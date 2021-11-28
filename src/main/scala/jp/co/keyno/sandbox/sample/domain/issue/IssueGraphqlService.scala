package jp.co.keyno.sandbox.sample.domain.issue

import jp.co.keyno.sandbox.sample.domain.repository.IssueRepository
import zio.{ Has, UIO, ULayer, URIO, ZIO, ZLayer }

object IssueGraphqlZioService {
  type IssueGraphqlZioService = Has[GraphqlZioService]

  trait GraphqlZioService {
    def getIssues: UIO[List[Issue]]
    def findIssue(id: Int): UIO[Option[Issue]]
  }

  def getIssues: URIO[IssueGraphqlZioService, List[Issue]] =
    URIO.accessM(_.get.getIssues)

  def findIssue(id: Int): URIO[IssueGraphqlZioService, Option[Issue]] =
    URIO.accessM(_.get.findIssue(id))

  def make(diRepository: IssueRepository): ULayer[Has[GraphqlZioService]] =
    ZLayer.succeed(new GraphqlZioService {
      private val repository = diRepository
      override def getIssues: UIO[List[Issue]] =
        ZIO.succeed(repository.findAll())
      override def findIssue(id: Int): UIO[Option[Issue]] =
        ZIO.succeed(repository.findIssue(id))
    })
}
