package jp.co.keyno.sandbox.sample

import com.google.inject.AbstractModule
import jp.co.keyno.sandbox.sample.domain.repository.IssueRepository
import jp.co.keyno.sandbox.sample.infrastructure.repository.IssueRepositoryImpl
import jp.co.keyno.sandbox.sample.usecase.service.{
  ApiSampleService,
  ApiSampleServiceImpl
}

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ApiSampleService]).to(classOf[ApiSampleServiceImpl])
    bind(classOf[IssueRepository]).to(classOf[IssueRepositoryImpl])
  }
}
