package RoomRentalSagar.RoomRentalSagar.RoomOwnersPackage;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomOwnersRepository extends JpaRepository<RoomOwners,Integer> {

     Optional<RoomOwners> findByEmailAndPassword(String email, String password);

     Optional<RoomOwners> findByEmail(String email);

     @Query("update RoomOwners rw set rw.password =:newPassword where rw.email=:email")
     @Transactional
     @Modifying
     void getUpdatePassword(@Param("email") String email,@Param("newPassword") String newPassword);

}
