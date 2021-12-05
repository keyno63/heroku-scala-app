package jp.co.keyno.sandbox.sample.domain.issue

import caliban.GraphQL.graphQL
import caliban.{ GraphQL, RootResolver }
import caliban.schema.GenericSchema
import caliban.wrappers.Wrappers.maxDepth
import jp.co.keyno.sandbox.sample.domain.issue.IssueGraphqlZioService.IssueGraphqlZioService
import zio.URIO

object IssueGraphqlZioApi
  extends GenericSchema[IssueGraphqlZioService]
  with IssueGraphqlApiTrait {

  // TODO: implement Mutation/Subscribe
  case class Queries(
    issues: IssuesQueryArgs => URIO[IssueGraphqlZioService, List[Issue]],
    issue: IssueQueryArgs => URIO[IssueGraphqlZioService, Option[Issue]]
  )

  case class Mutation(
    issue: IssueMutationArgs => URIO[IssueGraphqlZioService, Long],
    issueParams: IssueParamsMutationArgs => URIO[IssueGraphqlZioService, Long]
  )

  val api: GraphQL[IssueGraphqlZioService] = graphQL(
    RootResolver(
      Queries(
        _ => IssueGraphqlZioService.getIssues,
        args => IssueGraphqlZioService.findIssue(args.id)
      ),
      Mutation(
        args => IssueGraphqlZioService.insertIssue(args.issue),
        args =>
          IssueGraphqlZioService.insertIssue(
            InputIssue(args.summary, args.desc)
          )
      )
    )
  ) @@
    maxDepth(30)
}

trait IssueGraphqlApiTrait {
  case class IssuesQueryArgs()
  case class IssueQueryArgs(id: Int)
  case class IssueMutationArgs(issue: InputIssue)
  case class IssueParamsMutationArgs(summary: String, desc: String)
}
