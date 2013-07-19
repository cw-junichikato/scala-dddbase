package org.sisioh.dddbase.event

import org.sisioh.dddbase.core.lifecycle._
import org.sisioh.dddbase.core.model.Identity
import scala.language.higherKinds

/**
 * [[org.sisioh.dddbase.event.DomainEventStore]]のための骨格実装。
 *
 * @tparam R リポジトリの型
 * @tparam ID エンティティの識別子の型
 * @tparam T エンティティの型
 * @tparam M モナドの型
 */
trait DomainEventStoreSupport[+R <: Repository[ID, T, M], ID <: Identity[_], T <: DomainEvent[ID], M[+A]]
  extends DomainEventStore[R, ID, T, M, RepositoryWithEntity[R#This, T]] {

  protected val eventRepository: R

  def handleEvent(event: T): M[RepositoryWithEntity[R#This, T]] = {
    eventRepository.store(event).asInstanceOf[M[RepositoryWithEntity[R#This, T]]]
  }
}