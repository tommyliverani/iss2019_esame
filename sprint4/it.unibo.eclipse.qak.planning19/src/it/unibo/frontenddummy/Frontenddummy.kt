/* Generated by AN DISI Unibo */ 
package it.unibo.frontenddummy

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Frontenddummy ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("&&&  frontendDummy start")
					}
					 transition( edgeName="goto",targetState="sendPrepare", cond=doswitch() )
				}	 
				state("sendPrepare") { //this:State
					action { //it:State
						println("&&&  frontenddummy will send prepare msg to butlermind in 5 seconds")
						delay(5000) 
						println("&&&  frontenddummy sends now prepare msg")
						forward("prepare", "prepare()" ,"butlermind" ) 
					}
					 transition( edgeName="goto",targetState="sendPause", cond=doswitch() )
				}	 
				state("sendPause") { //this:State
					action { //it:State
						println("&&&  frontenddummy will send pause event in 5 seconds")
						delay(5000) 
						println("&&&  frontenddummy sends now pause event")
						emit("stopAppl", "stopAppl()" ) 
					}
					 transition( edgeName="goto",targetState="sendReactivate", cond=doswitch() )
				}	 
				state("sendReactivate") { //this:State
					action { //it:State
						println("&&&  frontenddummy will send reactivate event in 5 seconds")
						delay(5000) 
						println("&&&  frontenddummy sends now reactivate event")
						emit("reactivateAppl", "reactivateAppl()" ) 
					}
					 transition( edgeName="goto",targetState="sendAdd", cond=doswitch() )
				}	 
				state("sendAdd") { //this:State
					action { //it:State
						println("&&&  frontenddummy will send add msg to addtask in 40 seconds")
						delay(40000) 
						println("&&&  frontenddummy sends now add msg")
						forward("add", "add(foodCode)" ,"butlermind" ) 
					}
					 transition( edgeName="goto",targetState="sendClear", cond=doswitch() )
				}	 
				state("sendClear") { //this:State
					action { //it:State
						println("&&&  frontenddummy will send clear msg to cleartask in 30 seconds")
						delay(30000) 
						println("&&&  frontenddummy sends now clear msg")
						forward("clear", "clear()" ,"butlermind" ) 
					}
					 transition( edgeName="goto",targetState="workDone", cond=doswitch() )
				}	 
				state("workDone") { //this:State
					action { //it:State
						println("&&&  frontenddummy workDone")
					}
				}	 
			}
		}
}
