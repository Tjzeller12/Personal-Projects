
package stockCompare;

import java.util.ArrayList;
/**
 * Uses a quick sort algorithm with an average time complexity of O(n*log(n)). It sorts by rating or by name depending on the sortedBy param
 * @author Thomas Zeller
 *
 */
public class Sorter {
	final static int BY_RATING = 1;
	final static int BY_NAME = 0;
	/**
	 * 
	 * @param pList
	 * @param pFromIndex
	 * @param pToIndex
	 * @param sortedBy
	 * @return Right index (indexR)
	 */
	private static int partition(ArrayList<Stock> pList, int pFromIndex, int pToIndex, int sortedBy) {
		if (sortedBy == BY_RATING) {
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
		} else {
			Stock pivot = pList.get(pFromIndex);
			int indexL = pFromIndex - 1, indexR = pToIndex + 1;
			while (indexL < indexR) {
				indexL++;
				while(pList.get(indexL).getName().compareTo(pivot.getName()) < 0) {
					indexL++;
				}
				indexR--;
				while(pList.get(indexR).getName().compareTo(pivot.getName()) > 0) {
					indexR--;
				}
				if (indexL < indexR) {
					swap(pList, indexL, indexR);
				}
			}
			return indexR;
		}
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
	public static void sort(ArrayList<Stock> pList, int pFromIndex, int pToIndex, int sortedBy) {
		if (pFromIndex >= pToIndex) return;
		int partitionIndex = partition(pList, pFromIndex, pToIndex, sortedBy);
		sort(pList, pFromIndex, partitionIndex, sortedBy);
		sort(pList, partitionIndex + 1, pToIndex, sortedBy);
	}
}
