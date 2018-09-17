package org.tron.infura;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface EventLogRepository extends MongoRepository<EventLogEntity, String> {

  @Override
  List<EventLogEntity> findAll();

  @Query("{'transaction_id' : ?0}")
  List<EventLogEntity> findByTransactionId(String transactionId);

  @Query("{'contract_address' : ?0}")
  List<EventLogEntity> findByContractAddress(String contractAddress);

  @Query("{'contract_address' : ?0, 'event_name' : ?1}")
  List<EventLogEntity> findByContractAddressAndEntryName(String contractAddress, String entryName);

  @Query("{'contract_address' : ?0, 'event_name': ?1, 'block_number' : ?2}")
  List<EventLogEntity> findByContractAddressAndEntryNameAndBlockNumber(String contractAddress,
      String entryName, Long blockNumber);

  @Query("{ '$or' : [ {'block_timestamp' : ?0}, {'block_timestamp' : {$gt : ?0}} ]}")
  // return all event triggered after a certain timestamp
  List<EventLogEntity> findByBlockTimestampGreaterThan(Long timestamp);

}