package RoomRentalSagar.RoomRentalSagar.RoomRentalPackage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRentalRepository extends JpaRepository<RoomRental,Integer> {

     Optional<RoomRental> findByEmailAndPassword(String email,String password);

     Optional<RoomRental> findByEmail(String email);
}
