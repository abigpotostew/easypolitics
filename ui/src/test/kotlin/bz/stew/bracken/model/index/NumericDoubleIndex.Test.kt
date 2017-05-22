package bz.stew.bracken.model.index
//
//import bz.stew.bracken.controller.bill.filter.BillFilters
//
///**
// * Created by stew on 2/10/17.
// */
//class NumericDoubleIndexTest{
//    fun testIndexGreaterThan(){
//        val idx = object: NumericDoubleAbstractMappedIndex<DoubleWrapper>(){
//            override fun filterType(): BillFilters {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun map(inst: DoubleWrapper): Double {
//                return inst.value
//            }
//        }
//        val list= listOf<DoubleWrapper>(DoubleWrapper(0.0), DoubleWrapper(1.0),DoubleWrapper(2.0))
//        idx.indexInstances(list)
//
//        println((idx.instancesByOperator(IndexOperation.GreaterThanOrEqual, 1.0)))
//    }
//
//    private data class DoubleWrapper(val value:Double){}
//}