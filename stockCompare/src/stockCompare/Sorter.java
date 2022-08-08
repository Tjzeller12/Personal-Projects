
package stockCompare;

import java.util.ArrayList;
/**
 * Uses a quick sort algorithm with a time complexity of O(n*log(n))
 * @author Thomas Zeller
 *
 */
public class Sorter {
	/**
	 * 
	 * @param pList
	 * @param pFromIndex
	 * @param pToIndex
	 * @return Right index (indexR)
	 */
	private static int partition(ArrayList<Stock> pList, int pFromIndex, int pToIndex) {
		Stock pivot = pList.get(pFromIndex);
		int indexL = pFromIndex - 1, indexR = pToIndex + 1;
		while (indexL < indexR) {
			indexL++;
			while(pList.get(indexL).compareTo(pivot) > 0) {
				indexL++;
			}
			indexR--;
			while(pList.get(indexR).compareTo(pivot) < 0) {
				indexR--;
			}
			if (indexL < indexR) {
				swap(pList, indexL, indexR);
			}
		}
		return indexR;
	}
	/**
	 * Swaps a Stock at index1 with a Stock at index2
	 * @param pList
	 * @param index1
	 * @param index2
	 */
	private static void swap(ArrayList<Stock> pList, int index1, int index2) {
		Stock temp = pList.get(index1);
		pList.set(index1, pList.get(index2));
		pList.set(index2, temp);
	}
	/**
	 * Recursively sorts the list by partitioning
	 * @param pList
	 * @param pFromIndex
	 * @param pToIndex
	 */
	public static void sort(ArrayList<Stock> pList, int pFromIndex, int pToIndex) {
		if (pFromIndex >= pToIndex) return;
		
		int partitionIndex = partition(pList, pFromIndex, pToIndex);
		sort(pList, pFromIndex, partitionIndex);
		sort(pList, partitionIndex + 1, pToIndex);
	}
}
