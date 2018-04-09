package util

import org.slf4j.LoggerFactory

/**
  * Created by stittp on 12/05/2017.
  */
trait Logging extends AnyRef {
  @transient
  final protected lazy val log = LoggerFactory.getLogger(getClass.getName.stripSuffix("$"))
}