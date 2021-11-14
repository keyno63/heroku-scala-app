package jp.co.keyno.sandbox.sample.usecase.service

import jp.co.keyno.sandbox.sample.domain.issue.{ Issue, IssueService }

import javax.inject.Inject

trait ApiSampleService {
  def getValue(key: String): String
  def getIssueList: List[Issue]
}

class ApiSampleServiceImpl @Inject() (issueService: IssueService)
  extends ApiSampleService {
  override def getValue(key: String): String = s"value: $key"
  override def getIssueList: List[Issue] = {
    issueService.getIssueList
  }
}
