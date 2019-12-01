/* Generated by AN DISI Unibo */ 
package it.unibo.fridge

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Fridge ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						solve("consult('fridgeInit.pl')","") //set resVar	
						println("&&&  fridge STARTED")
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("waitCmd") { //this:State
					action { //it:State
						println("&&&  fridge waiting for command")
					}
					 transition(edgeName="t013",targetState="putTask",cond=whenDispatch("put"))
					transition(edgeName="t014",targetState="showStateTask",cond=whenDispatch("showState"))
					transition(edgeName="t015",targetState="getTask",cond=whenDispatch("get"))
					transition(edgeName="t016",targetState="checkTask",cond=whenDispatch("isAvailable"))
				}	 
				state("showStateTask") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						solve("getFridgeState(L)","") //set resVar	
						if(currentSolution.isSuccess()) { 
										val FridgeState = itunibo.prolog.prologUtils.parseFoodState(myself,"L")
						forward("state", "state($FridgeState)" ,"serverproxy" ) 
						 }
						else
						{ println("getFridgeState FAIL")
						 }
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("getTask") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("get(ARG)"), Term.createTerm("get(ARG)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 
												val Food = payloadArg(0)
								solve("removeFoodList($Food)","") //set resVar	
								if(currentSolution.isSuccess()) { println("Food = $Food")
								forward("put", "put($Food)" ,"serverproxy" ) 
								 }
								else
								{ println("fridgeGet FAIL")
								 }
						}
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("putTask") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("put(ARG)"), Term.createTerm("put(ARG)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								println("$name in ${currentState.stateName} | $currentMsg")
								 val Food = payloadArg(0) 
								solve("addFoodList($Food)","") //set resVar	
								if(currentSolution.isSuccess()) { forward("remove", "remove($Food)" ,"serverproxy" ) 
								 }
								else
								{ println("fridgePut FAIL")
								 }
						}
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("checkTask") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("isAvailable(ARG)"), Term.createTerm("isAvailable(ARG)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								println("$name in ${currentState.stateName} | $currentMsg")
								 val FoodCode = payloadArg(0) 
								solve("isThereFoodByCode($FoodCode)","") //set resVar	
								if(currentSolution.isSuccess()) { forward("yes", "yes($FoodCode)" ,"serverproxy" ) 
								 }
								else
								{ forward("no", "no($FoodCode)" ,"serverproxy" ) 
								 }
						}
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
			}
		}
}
