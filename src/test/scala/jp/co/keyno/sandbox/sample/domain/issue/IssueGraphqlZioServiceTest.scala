package jp.co.keyno.sandbox.sample.domain.issue

import jp.co.keyno.sandbox.sample.domain.issue.IssueGraphqlZioService.IssueGraphqlZioService
import jp.co.keyno.sandbox.sample.domain.repository.IssueRepository
import munit.FunSuite
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{ doReturn, mock }
import zio.ULayer

class IssueGraphqlZioServiceTest extends FunSuite {
  private val mockIssueRepository = mock(classOf[IssueRepository])

  test("findAll のテスト") {
    val expected = List(Issue(1, "タイトル1", "概要1"))
    doReturn(expected)
      .when(mockIssueRepository)
      .findAll()

    val target: ULayer[IssueGraphqlZioService] =
      IssueGraphqlZioService.make(mockIssueRepository)

    def testFunc(target: ULayer[IssueGraphqlZioService]) =
      zio.Runtime.default.unsafeRun(
        IssueGraphqlZioService.getIssues.provideLayer(target)
      )

    assertEquals(testFunc(target), expected)
  }

  test("findIssue のテスト") {
    val expected = Some(Issue(1, "タイトル1", "概要1"))
    doReturn(expected)
      .when(mockIssueRepository)
      .findIssue(any())

    val target: ULayer[IssueGraphqlZioService] =
      IssueGraphqlZioService.make(mockIssueRepository)

    def testFunc(target: ULayer[IssueGraphqlZioService], arg: Int) =
      zio.Runtime.default.unsafeRun(
        IssueGraphqlZioService.findIssue(arg).provideLayer(target)
      )

    assertEquals(testFunc(target, expected.map(_.id).get), expected)
  }

  test("insertIssue のテスト") {
    val expected = Issue(1, "タイトル1", "概要1")
    doReturn(expected.id.toLong)
      .when(mockIssueRepository)
      .insertIssue(any())

    val target: ULayer[IssueGraphqlZioService] =
      IssueGraphqlZioService.make(mockIssueRepository)

    def testFunc(target: ULayer[IssueGraphqlZioService], arg: InputIssue) =
      zio.Runtime.default.unsafeRun(
        IssueGraphqlZioService.insertIssue(arg).provideLayer(target)
      )

    assertEquals(
      testFunc(target, InputIssue(expected.summary, expected.desc)),
      expected.id.toLong
    )
  }
}
