package bz.stewart.bracken.db.database.index

import com.fasterxml.jackson.annotation.JsonProperty
import com.mongodb.BasicDBObject
import org.junit.Test
import kotlin.test.assertEquals

class IndexedFieldTest {
    @Test
    fun testJsonPropertyAnnotation() {
        val prop = IndexedKPropertyTestClass::prop1
        assertEquals("PROP_1", prop.getJsonPropertyNameDefault(IndexedKPropertyTestClass::class))

        val propNested = IndexedKPropertyTestClass::nestedProp
        assertEquals("nestedProp", propNested.getJsonPropertyNameDefault(IndexedKPropertyTestClass::class))
    }

    @Test
    fun testFields() {
        val index = IndexFieldBuilder.create(IndexedKPropertyTestClass::prop1,
            IndexedKPropertyTestClass::class,
            IndexSortTypes.DESCENDING)
        assertEquals("PROP_1_-1", index.name)

        val indexNested = IndexFieldBuilder.createText(IndexedKPropertyTestClass::nestedProp,
            IndexedKPropertyTestClass::class,
            IndexedNestedClass::innerProp,
            IndexedNestedClass::class)
        assertEquals("nestedProp.NESTED_PROP__text", indexNested.name)
    }

    @Test
    fun testBson() {
        val index = IndexFieldBuilder.create(IndexedKPropertyTestClass::prop1,
            IndexedKPropertyTestClass::class,
            IndexSortTypes.DESCENDING)
        val expected = BasicDBObject()
        expected.put("PROP_1", -1)
        assertEquals(expected.toJson(), index.toBson().toJson())
    }

    class IndexedKPropertyTestClass(@JsonProperty("PROP_1") val prop1: String, val nestedProp: Array<IndexedNestedClass>)
    class IndexedNestedClass(@JsonProperty("NESTED_PROP_") val innerProp: String)
}