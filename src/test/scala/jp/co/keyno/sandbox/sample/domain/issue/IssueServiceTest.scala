package jp.co.keyno.sandbox.sample.domain.issue

import munit.FunSuite
import org.mockito.MockitoSugar

class IssueServiceTest extends FunSuite with MockitoSugar {
  private val mock = mock[IssueService]

  test("first munit test") {
    val expected = List(Issue(1, "タイトル1", "概要1"))
    doReturn(expected)
      .when(mock)
      .getIssueList

    assertEquals(mock.getIssueList, expected)
  }
}
