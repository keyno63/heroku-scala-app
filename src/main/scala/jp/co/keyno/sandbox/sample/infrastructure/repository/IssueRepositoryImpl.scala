package jp.co.keyno.sandbox.sample.infrastructure.repository

import jp.co.keyno.sandbox.sample.domain.issue.Issue
import jp.co.keyno.sandbox.sample.domain.repository.IssueRepository
import scalikejdbc.{
  ConnectionPool,
  DB,
  scalikejdbcSQLInterpolationImplicitDef
}

class IssueRepositoryImpl extends IssueRepository {

  Class.forName("org.postgresql.Driver")
  ConnectionPool.singleton(
    "jdbc:postgresql://localhost:15432/world",
    "root",
    "password"
  )

  def findAll(): List[Issue] = {
    DB readOnly { implicit session =>
      sql"SELECT id, summary, description FROM issues"
        .map { rs =>
          Issue(
            rs.int("id"),
            rs.string("summary"),
            rs.string("summary")
          )
        }
        .toList
        .apply()
    }
  }

  def findIssue(id: Int): List[Issue] = {
    DB readOnly { implicit session =>
      sql"SELECT id, summary, description FROM issues WHERE id = ${id}"
        .map { rs =>
          Issue(
            rs.int("id"),
            rs.string("summary"),
            rs.string("summary")
          )
        }
        .toList
        .apply()
    }
  }
}
