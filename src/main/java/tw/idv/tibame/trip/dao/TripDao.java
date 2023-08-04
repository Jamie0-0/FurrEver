package tw.idv.tibame.trip.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import tw.idv.tibame.trip.vo.Trip;

@Component
public interface TripDao extends CrudRepository<Trip, Integer>{

//	@Query(value = "select ")
}
