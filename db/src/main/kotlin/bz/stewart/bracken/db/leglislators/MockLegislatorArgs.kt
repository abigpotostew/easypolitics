package bz.stewart.bracken.db.leglislators

import java.io.File

/**
 * Created by stew on 6/4/17.
 */
class MockLegislatorArgs (_files:MutableList<File> = mutableListOf<File>(),
                          _testMode:Boolean = true,
                          _dbName:String = "default_legislator_db_name",
                          _socialFile:File? = null):LegislatorArguments{
   override var files: MutableList<File> = _files
   override var testMode: Boolean = _testMode
   override var dbName: String = _dbName
   override var socialFile: File? = _socialFile
}