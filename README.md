# RoomViewModelDemo
Room Database and ViewModel Android Architecture Components Demo

# Room Database
  Room provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
  
# Create Room Database in 4 Steps :
    1- add room dependency to module.app.
      
      def room_version = "1.1.1"
      implementation "android.arch.persistence.room:runtime:$room_version"
      annotationProcessor "android.arch.persistence.room:compiler:$room_version" // use kapt for Kotlin
    
    2- create class entity that hold data that represent table data.
       @Entity(tableName = "players")
       public class PlayerEntity {
        //table info (column)
        //..
       }
    3- create interface DAO that represent query for entity.
      @Dao
      public interface PlayerDAO {
      //Database queries
      }
    4- create class that extend RoomDatabase to create Room Database object and use it
      @Database(entities = {PlayerEntity.class}, version = 1, exportSchema = false)
      public abstract class AppDatabase extends RoomDatabase {
                 //create database single instance
                 }

# ViewModel :
    The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
    The ViewModel class allows data to survive configuration changes such as screen rotations.

# One 2 Steps to use ViewModel
    1- add ViewModel dependency to module.app.
        implementation "android.arch.lifecycle:viewmodel:$lifecycle_version" // use -ktx for Kotlin
    2- create class that extend ViewModel 
        public class PlayerInfoViewModel extends ViewModel {
        // All Ui state 
         }

    
