
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
		//System.out.println(levenshtein("love", "loVer"));
	}

	public static String tail(String str) {
		String newString = "";
		if(str.length()==1)
		{
			newString = "";
		}
		else {
			newString = str.substring(1);
		}
		return newString;
	}

	public static int levenshtein(String word1, String word2) {
		if(word1.length()==0)
			return word2.length();
		if(word2.length()==0)
			return word1.length();

		int distance =0;
		if(word1.charAt(0) == word2.charAt(0))
		{
			return levenshtein(tail(word1), tail(word2));
		}
		else
		{
			int a = levenshtein(tail(word1), word2);
			int b = levenshtein(word1, tail(word2));
			int c = levenshtein(tail(word1), tail(word2));
			distance = Math.min(a, b);
			distance = Math.min(distance, c);
		}
		return 1+distance;

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i = 0; i<3000; i++)
		{
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String mostSimilar = "";
		int minimalDistance = word.length();
		for(int i=0; i<3000; i++)
		{
			int currentDistance = levenshtein(dictionary[i], word);
			if(currentDistance==0)
			{
				return word;
			}
			if((currentDistance<=threshold)&&(currentDistance < minimalDistance))
			{
				minimalDistance = currentDistance;
				mostSimilar = dictionary[i];
			}

		}

		if(mostSimilar.isEmpty())
			return word;
		return mostSimilar;
	}

}
