package com.api.springboot.parkingtoll.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.api.springboot.parkingtoll.entity.Slot;
import com.api.springboot.parkingtoll.utils.SlotUtils;

@Repository
public class SlotDaoImpl implements SlotDao {

	private EntityManager entityManager;

	@Autowired
	public SlotDaoImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public Slot findAvailableSlot(String type) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Slot> query = currentSession.createQuery("from Slot where vehicle_type=:type AND is_available=true");
		query.setParameter("type", type.toUpperCase());
		query.setMaxResults(1);

		// execute query and return result
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public Slot addVehicle(Slot slot, String vehicleRegistration) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// mark slot as taken
		SlotUtils.blockSlot(slot, vehicleRegistration);

		// Update DB
		currentSession.saveOrUpdate(slot);

		return slot;
	}

	@Override
	public Slot getAssociatedSlotToVehicle(String vehicleRegistration) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// create a query
		Query<Slot> query = currentSession.createQuery("from Slot where vehicle_registration=:vehicleRegistration");
		query.setParameter("vehicleRegistration", vehicleRegistration.toUpperCase());

		List<Slot> result = query.getResultList();
		if (CollectionUtils.isEmpty(query.getResultList())) {
			return null;
		}

		return result.get(0);
	}
	
	/**
	 * remove Vehicle from Slot and mark it as available
	 */
	@Override
	public void removeVehicle(Slot slot) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// free slot
		SlotUtils.freeSlot(slot);

		// Update DB
		currentSession.saveOrUpdate(slot);
	}
}