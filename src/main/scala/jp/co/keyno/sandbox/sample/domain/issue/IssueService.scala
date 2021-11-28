package jp.co.keyno.sandbox.sample.domain.issue

import jp.co.keyno.sandbox.sample.domain.repository.IssueRepository

import javax.inject.Inject

class IssueService @Inject() (
  repository: IssueRepository
) {

  def getIssueList: List[Issue] = {
    repository.findAll()
  }

  def getIssueListById(id: Int): Option[Issue] = {
    repository.findIssue(id)
  }
}
