package jp.co.keyno.sandbox.sample.domain.issue

import jp.co.keyno.sandbox.sample.domain.repository.IssueRepository
import jp.co.keyno.sandbox.sample.infrastructure.repository.IssueRepositoryImpl

import javax.inject.Inject

class IssueService @Inject() (
  repository: IssueRepository
) {

  def getIssueList: List[Issue] = {
    repository.findAll()
  }
}
