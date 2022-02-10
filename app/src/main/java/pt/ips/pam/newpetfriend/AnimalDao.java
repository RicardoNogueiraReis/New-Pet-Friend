package pt.ips.pam.newpetfriend;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AnimalDao {
    @Query("SELECT * FROM Animal")
    List<Animal> getAll();

    @Query("SELECT * FROM Animal WHERE id = :id LIMIT 1")
    Animal findById(int id);

    @Query("SELECT * FROM Animal WHERE nome_animal LIKE :nomeAnimal")
    List<Animal> findByName(String nomeAnimal);

    @Insert
    void insert (Animal... animals);

    @Update
    void update(Animal... animals);

    @Delete
    void delete(Animal animal);


}
