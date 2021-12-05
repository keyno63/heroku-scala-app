package jp.co.keyno.sandbox.sample.infrastructure.repository

import jp.co.keyno.sandbox.sample.domain.issue.{ InputIssue, Issue }
import jp.co.keyno.sandbox.sample.domain.repository.IssueRepository
import jp.co.keyno.sandbox.sample.domain.config.Config
import scalikejdbc.{
  ConnectionPool,
  DB,
  scalikejdbcSQLInterpolationImplicitDef
}

import javax.inject.Inject

class IssueRepositoryImpl @Inject() (config: Config) extends IssueRepository {

  Class.forName(config.db.driverName)
  ConnectionPool.singleton(
    config.db.url,
    config.db.user,
    config.db.password
  )

  def findAll(): List[Issue] = {
    DB readOnly { implicit session =>
      sql"SELECT id, summary, description FROM issues"
        .map { rs =>
          Issue(
            rs.int("id"),
            rs.string("summary"),
            rs.string("description")
          )
        }
        .toList
        .apply()
    }
  }

  def findIssue(id: Int): Option[Issue] = {
    DB readOnly { implicit session =>
      sql"SELECT id, summary, description FROM issues WHERE id = ${id}"
        .map { rs =>
          Issue(
            rs.int("id"),
            rs.string("summary"),
            rs.string("description")
          )
        }
        .single
        .apply()
    }
  }

  override def insertIssue(issue: InputIssue): Long = {
    DB autoCommit { implicit session =>
      sql"""INSERT
            INTO
                issues
                (summary, description)
            VALUES
                (${issue.summary}, ${issue.desc})
         """.updateAndReturnGeneratedKey
        .apply()
    }
  }
}
