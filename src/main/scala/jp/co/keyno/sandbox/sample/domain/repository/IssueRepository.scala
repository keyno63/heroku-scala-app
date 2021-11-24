package jp.co.keyno.sandbox.sample.domain.repository

import jp.co.keyno.sandbox.sample.domain.issue.Issue

trait IssueRepository {
  def findAll(): List[Issue]
  def findIssue(id: Int): List[Issue]
}
