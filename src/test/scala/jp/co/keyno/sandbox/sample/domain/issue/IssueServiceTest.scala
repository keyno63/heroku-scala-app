package jp.co.keyno.sandbox.sample.domain.issue

import jp.co.keyno.sandbox.sample.domain.repository.IssueRepository
import munit.FunSuite
import org.mockito.Mockito.{ doReturn, mock }
import org.mockito.MockitoSugar

class IssueServiceTest extends FunSuite with MockitoSugar {
  private val mock = mock[IssueRepository]

  test("first munit test") {
    val expected = List(Issue(1, "タイトル1", "概要1"))
    doReturn(expected)
      .when(mock)
      .findAll()

    val service = new IssueService(mock)

    assertEquals(service.getIssueList, expected)
  }
}

class IssueServiceUsedMockitoTest extends FunSuite {
  private val mockIssueService = mock(classOf[IssueRepository])

  test("IssueService Test used Mockito") {
    val expected = List(Issue(1, "タイトル1", "概要1"))
    doReturn(expected)
      .when(mockIssueService)
      .findAll()

    val service = new IssueService(mockIssueService)

    assertEquals(service.getIssueList, expected)
  }
}
