package leidenuniv.symbolicai;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import leidenuniv.symbolicai.logic.KB;
import leidenuniv.symbolicai.logic.Predicate;
import leidenuniv.symbolicai.logic.Sentence;
import leidenuniv.symbolicai.logic.Term;

public class MyAgent extends Agent {
	
	

	@Override
	public KB forwardChain(KB kb) {
		//This method should perform a forward chaining on the kb given as argument, until no new facts are added to the KB.
		//It starts with an empty list of facts. When ready, it returns a new KB of ground facts (bounded).
		//The resulting KB includes all deduced predicates, actions, additions and deletions, and goals.
		//These are then processed by processFacts() (which is already implemented for you)
		//HINT: You should assume that forwardChain only allows *bound* predicates to be added to the facts list for now.
		
		return null;
	}

	@Override
	public boolean findAllSubstitions(Collection<HashMap<String, String>> allSubstitutions,
			HashMap<String, String> substitution, Vector<Predicate> conditions, HashMap<String, Predicate> facts) {
		//Recursive method to find *all* valid substitutions for a vector of conditions, given a set of facts
		//The recursion is over Vector<Predicate> conditions (so this vector gets shorter and shorter, the farther you are with finding substitutions)
		//It returns true if at least one substitution is found (can be the empty substitution, if nothing needs to be substituted to unify the conditions with the facts)
		//allSubstitutions is a list of all substitutions that are found, which was passed by reference (so you use it build the list of substitutions)
		//substitution is the one we are currently building recursively.
		//conditions is the list of conditions you  still need to find a subst for (this list shrinks the further you get in the recursion).
		//facts is the list of predicates you need to match against (find substitutions so that a predicate form the conditions unifies with a fact)
		
		if(conditions.isEmpty()) {
			allSubstitutions.add(substitution);
			return true;
		}
		Predicate currentCondition = conditions.firstElement();
		conditions.remove(0);
		
		currentCondition = substitute(currentCondition, substitution);
		
		HashMap<String, String> currentSubstitution = new HashMap<String, String>();
		boolean subCheck = false; //
		
		if(currentCondition.not || currentCondition.eql) {
			//loop over feiten, haal alle namen van termen er uit 
			// voeg alle substituties toe voor not of voor eql afhankelijk van casus
		}
		
		else {
			for (Predicate fact: facts.values()) {
				currentSubstitution = unifiesWith(currentCondition, fact);
				if (currentSubstitution != null) {
					for (HashMap.Entry<String, String> sub : currentSubstitution.entrySet()) {
						substitution.put(sub.getKey(), sub.getValue());
					}
					if( findAllSubstitions(allSubstitutions, substitution, conditions, facts)) {
						subCheck = true;
					}
				}
				
			}
		}
		
		
		
		
		return subCheck;
	}

	@Override
	public HashMap<String, String> unifiesWith(Predicate p, Predicate f) {
		//Returns the valid substitution for which p predicate unifies with f
		//You may assume that Predicate f is fully bound (i.e., it has no variables anymore)
		//The result can be an empty substitution, if no subst is needed to unify p with f (e.g., if p an f contain the same constants or do not have any terms)
		//Please note because f is bound and p potentially contains the variables, unifiesWith is NOT symmetrical
		//So: unifiesWith("human(X)","human(joost)") returns X=joost, while unifiesWith("human(joost)","human(X)") returns null 
		//If no subst is found it returns null
		
		if(! p.getName().equals(f.getName())) {
			return null;
		}
		
		HashMap<String, String> substitutions = new HashMap<String, String>();
		Vector<Term> fTerms = f.getTerms();
		Vector<Term> pTerms = p.getTerms();
		
		if(fTerms.size() != pTerms.size()) { //predicates of unequal size can never unify
			return null;
		}
		
		for (int i = 0; i < fTerms.size(); i++) {
			if(pTerms.get(i).var) {
				substitutions.put(pTerms.get(i).term, fTerms.get(i).term); //if the p-term is a variable, add the constant term of f as a valid substitution
			}
			else {
				if (! pTerms.get(i).term.equals(fTerms.get(i).term)) {
					return null; //if a constant term of p is different from one in f there is no valid substitution
				}
			}
		}
		
		return substitutions;
	}

	@Override
	public Predicate substitute(Predicate old, HashMap<String, String> s) {
		// Substitutes all variable terms in predicate <old> for values in substitution <s>
		//(only if a key is present in s matching the variable name of course)
		//Use Term.substitute(s)
		
		Predicate copy = new Predicate(old.toString()); 
		
		for (Term X : copy.getTerms()) {
			X.substitute(s);
		}
		
		return copy;
	}

	@Override
	public Plan idSearch(int maxDepth, KB kb, Predicate goal) {
		//The main iterative deepening loop
		//Returns a plan, when the depthFirst call returns a plan for depth d.
		//Ends at maxDepth
		//Predicate goal is the goal predicate to find a plan for.
		//Return null if no plan is found.
		return null;
	}

	@Override
	public Plan depthFirst(int maxDepth, int depth, KB state, Predicate goal, Plan partialPlan) {
		//Performs a depthFirst search for a plan to get to Predicate goal
		//Is a recursive function, with each call a deeper action in the plan, building up the partialPlan
		//Caps when maxDepth=depth
		//Returns (bubbles back through recursion) the plan when the state entails the goal predicate
		//Returns null if capped or if there are no (more) actions to perform in one node (state)
		//HINT: make use of think() and act() using the local state for the node in the search you are in.
		return null;
	}
}
