package jp.co.keyno.sandbox.sample.domain.issue

class IssueService {

  // TODO: fetch from repository
  def getIssueList: List[Issue] = {
    (1 to 3)
      .map(i => Issue(i, s"概要$i", s"説明$i"))
      .toList
  }
}
