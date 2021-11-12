package jp.co.keyno.sandbox.sample.usecase.service

trait ApiSampleService {
  def getValue(key: String): String
}

class ApiSampleServiceImpl extends  ApiSampleService {
  override def getValue(key: String): String = s"value: $key"
}
