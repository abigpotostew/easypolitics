package bz.stewart.bracken.web.html.config

import kotlinx.html.HTMLTag
import kotlin.reflect.KClass

class HtmlConfiguration (val tagConfigurations:Set<out TagConfiguration<HTMLTag>>){
    fun getWithTag(type:KClass<out HTMLTag>):List<TagConfiguration<HTMLTag>>{
        val out = mutableListOf<TagConfiguration<HTMLTag>>()
        for(conf in tagConfigurations){
            if(conf.isType(type)){
                out.add(conf)
            }
        }
        return out
    }
}

abstract class TagConfiguration <T : HTMLTag> (type:KClass<T>, attributes:Map<String,String>) {//, conf:T.()->Unit
    val type = type
    val attributes = attributes
    protected abstract fun doConfig(tag:T)
    fun apply(tag:T){
        for ((k,v) in attributes.entries){
            tag.attributes.put(k,v)
        }
        doConfig(tag)
    }
    fun isType(type:KClass<out HTMLTag>):Boolean{
        return this.type.equals(type)
    }
}

class BasicConfig<T : HTMLTag>(type:KClass<T>, attributes:Map<String,String>, content:String?=null):TagConfiguration<T>(type, attributes){
    val content = content
    override fun doConfig(tag: T) {
        if(content!=null){
            tag.content
        }
    }
}