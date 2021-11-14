package jp.co.keyno.sandbox.sample.application.controller

import jp.co.keyno.sandbox.sample.usecase.service.ApiSampleService

import javax.inject.Inject

class ApiSampleController @Inject() (
  service: ApiSampleService
) {
  def ok(key: String): String = {
    service.getValue(key)
  }

  def findIssueList: String = {
    service.getIssueList.toString()
  }
}
