/**
 * INF2010 - ASD
 * Table de dispersement avec resolution des collisions par
 * sondage linéaire (Linear Probing Hash Table).
 * Ce code est basé sur Chapitre 5 de *Data Structures and Algorithms
 * Analysis in Java* (2e ed.) de Mark Allen Weiss, avec modifications
 * par Susanna Rumsey (2026).
 *
 */
public class DoubleHashingHashTable<AnyType> extends ProbingHashTable<AnyType>{
    /**
     * TODO: À remplir en utilisant hashage double ou f(i) = i*myhash(x).  Astuce : examinez le code pour la
     * methode findPos dans QuadraticProbingHashTable pour commencer.
     */
    protected int findPos(AnyType x) {
        int currentPos = super.myhash(x); // premier hash, celui de HashTable
        int step = -1;

        // case occupee par un autre element : on avance de h2(x) cases
        while (array[currentPos] != null &&
                !array[currentPos].element.equals(x)) {
            collisionCounter++;
            if (step == -1) {
                step = myhash(x); // deuxieme hash, calcule une seule fois
            }
            currentPos += step;
            if (currentPos >= array.length) {
                currentPos -= array.length; // on revient au debut
            }
        }
        return currentPos;
    }
    
    @Override
    protected int myhash(AnyType x) {
      if (MATRICULE == 0) {
        throw new ArithmeticException("Entrez votre matricule dans DoubleHashingHashTable.java avant de proceder.");
      }
      int hashVal = x.hashCode();
      int length = this.tableLength();
      int R = nextPrime(MATRICULE % length);
      while (R >= length){
        R -= length;
        R = nextPrime(R);
      }
      return R - (hashVal % R);
    }

    public static void main(String[] args) {
        DoubleHashingHashTable<Integer> table = new DoubleHashingHashTable<>();
        test(table);
    }
}
