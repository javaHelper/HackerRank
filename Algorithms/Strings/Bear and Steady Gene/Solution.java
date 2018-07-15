import java.util.Scanner;
import java.util.function.Function;

/**
 * @author Oleg Cherednik
 * @since 14.07.2018
 */
public class Solution {

    static int steadyGene(String gene) {
        int[] arr = new int[4];

        for (int i = 0; i < gene.length(); i++)
            arr[POS.apply(gene.charAt(i))]++;

        int max = gene.length() / 4;

        if (arr[0] == max && arr[1] == max && arr[2] == max && arr[3] == max)
            return 0;

        int res = Integer.MAX_VALUE;
        int[] tmp = new int[4];

        for (int i = 0, j = 0; j < gene.length() && i <= j; ) {
            if ((arr[0] > max && tmp[0] < arr[0] - max)
                    || (arr[1] > max && tmp[1] < arr[1] - max)
                    || (arr[2] > max && tmp[2] < arr[2] - max)
                    || (arr[3] > max && tmp[3] < arr[3] - max)) {
                tmp[POS.apply(gene.charAt(j++))]++;
            } else {
                res = Math.min(res, j - i);
                tmp[POS.apply(gene.charAt(i++))]--;
            }
        }

        return res;
    }

    private static final Function<Character, Integer> POS = ch -> {
        if (ch == 'A')
            return 0;
        if (ch == 'C')
            return 1;
        if (ch == 'T')
            return 2;
        if (ch == 'G')
            return 3;
        return -1;
    };

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 5
        // 1393
        // 5
        // 0
        System.out.println(steadyGene("GAAATAAA"));
        System.out.println(steadyGene(
                "ACAAAAATAAACAAAAACAAAAAAAAAATAAATACAATAAAAAAAAAAAATGAAATACAACAACAAATAAAATAAAAACGACTAAAAAATAAAAAAAAAAAAAAAAAGAGTACTAAAAAAAAAAAAAAAAAATAAAAAAAAAAAAAACACAATCAAAATAAACAAAAAAAAAAAAACCAAAATAATCAACAAAAAAAAAAAAAACAAAAACAACAACAAACAAAAAAAAACACAAACAAAAAAAAAAAAAAAACAAAACAAACAAAAAAAAAAAAACAAAAAAACAAAAAAAAAAAAAAAAACAAAAAAAAAAATAAAAAAAAAAAAAAAAAAAAAACAAACAAAAAAAAAAAATACAAAAAGCTATAAAAAAAAAAAAATTAAAAAACAAAAAAAAATAAAAAAAAAAAAAAAAAAAAAAAATAAAAAAAAAAAAAAAAAAAAAATAAAAAAAAAAAAAAAAAAGAAAAACAAAAAAAAAAAAAAAAACAACCAAAAAACAAAAAAAAACTAAAAAAAAAAAAAAAAAAAAAAAAAAATAACAAAAAACACAAAAAAAAAAAAGAAAGAAAAAAAACACAAAAAAAAACAAACAAAAAAAAAAAAAAAAAAAGAAAACAAAAAAACAAAAAAAACAAAAAAAAAACAAAAATTGGACAAAAAAAAACAAAAAAAAAAAACAAAAAAAGTAAAACAAATAAAAAAACAAAAAAAACAAAAAAAAAAAAAAAAAACAAAAAAGAAACAAAAAACAAAAAAAAATAACAAAACCAAAAAACAAATAAAAAACAAAAAAAATAACACAAAAAAAAAAAGAAACAAAAAAAAAAAAAAAAAAAAAAATTATAAAAAAAAAAAAAAAACAAAAAAAAAAAAAACAAAAAAAAAAGGAAAAAAAAAAAAAAAAAAAAAAAAAAATAACTAAACAAAAAAAAACAAACAAAAAATCAAAAAAAAAAAAGAAAAAAGAATAAGCAACAAAAACACAAAAAAAAAAAAAAAAAAAAAAAACATAAACAATAATAAAAAAAAAACAAAAAAAACAAAAGAACAACAAAAAACAAAACTAAACAAATAAAAAAAAAAAAACAAAAACTACAAAAAAAAAAAGAAAAAAAAAGAAAAAAAAACAAATAAAAGAAAAAAAAAAAAAAAAAAAACACAAAAAAAAAAATAAAAAAAAAAAAAAAAACAAAATAAACAAAAACAAAGAAAAAAACAAACAAAAAAAAAAAACAAAAAACTAAAAACAAAAAAAAAACAAAACACAAAAAAAAAAAAAAATAAAAAAAAAACAAAAAAACAAAAAGGAAAAAAAAAAAAGAACAAAAAAAAAAACAACAGAAAAAAGAAAAGAAAAAAAAAAAAAGACCACAAAATAAAAAAAAACAACAAACAAAAAAAAACAAAACAAAAAAACGAACAAAAAAAACAAAAACAAAAAAAAAAAAAAAAAAAAAAAGGCAAAAACAAAAAAAACAAAACAAAACAAAAAAACAAAAAAAAATTAAGATAAAGAACAAAAAAAGAAGAGAAAAAATTAACAAAAAAAAAAAAATAAAAAATACAAAAAGAAATAAAAAATACAACACACAACAAAAACGAAAAAAAAAAAAAAAACACAAAATAGAAAAAAAAAAAAAACAAAAAAAAAAAAAAGAAAAAAACAAAAAAAAAAAAATAAAAAAAAACGACACAGAAACAAAAAATAACAAAAAAAAAAAAAATAAAAAAAAAACAAAAAAAAAACAAAAAATAAAAAAAAAAACAAACAAAAAAAAAAAAAAAATAAAAAAAAAAAAAGCAAAACATAAACAAGAAAAAAAAAAAAAGTACAAATAACAAAACAAAAAAGACACTAAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAAAAAAAAGAAAAAAAACCACAAAACAAAAAAATAAAGCAAAAAAAAAAAAAAAAAAAAAAAAAAAATAAATGAAAAAAAAAAGAAAACCAAAAAAATAAAAGA"));
        System.out.println(steadyGene("TGATGCCGTCCCCTCAACTTGAGTGCTCCTAATGCGTTGC"));  //5
        System.out.println(steadyGene(
                "TATATAGCTTGTCTCCCTAATGTTAGTTCATGCTCGTAAGAGAACTTAGCCTACTAGGACGAGAGAACCGCACGGCGTCGTGAGGTATTTTTCGTAGGACACGCCAGATAGACGGTGGCAATGCCCGTTCAATATGACGCGATGTACGGCTAATGGGAACACTGCCCGACGCGTCTTTAGGACTGTGAGTTGCGGGTTACAGCTATGGTCTTATTGGTATCCGGCCCCTTTCGAGTCGCGATGCGCCTGCCACCACGATATTCGCCCGAAACGCGATTTGTGGGCGAGGTAGTCGTGTTCAACCCTGTAAATTTCCCTAGGTATAATCGTTCTAAGGTTCGCACATACACATCCACACCTACCTTTACACAGTTCGAGGTTCTATACGTCCTCTGAGTGCGTGTTAACACGCCCGTAAATGGGCATTTGGAGTCAGACCAGTACTTTGCGATAAACTTTACTTCCGCGAGACCTGTCCCTGGAACCCTGTTGTAAGGGTTAGGGTTTAATAGCTCCATGTCGTGTGCCTATAAGAAAAGGACGAATGGTGACAGTCCGGCTTAGCCAGGACAATGCGTGGCTGACGACGTCCAGGGTAAATTGAGTTGAATTCGCCTAATTTTAGGGTGTCTTGGTTCAATGAGGTGTCGACTTAACAAAAGGCGACATCAGTTGTCATCTTGCCTTGATAAAGTAAAACACGTGAATAGCCTATCCGGTCTGACCCCCGGGCCATGTGCTTCACCCAGGGAGCATCGCCGCTCTAGAGACGGTGTTCGTAGTCTCGATAACATGTGGGGTAATATAGAATATCCAAGACCGGTAGGAGGGGCGGTTCCGCGTCATAAGAAGTCCCAACGTGGCCTGCCACGTTCAAACAGGATACGCTATAACAGCTTCGTGGGTAATTGATGGATACGCCCGCAGGCTACCCATGCTCTTGCGATTTTGCAACCCTCGGAACCGTCACTCGTACACCCAGACATCATCTCATACAATTGCCTCACCTTCATGCCGGTACATAGGTGCCATCTCCGCTTAAGAATCCTCGCAGCAATTAATGTGACAGCACGCTAGTCCACTAGCGTATGATTACGCCACCGGGCCACCATGGACAAAAACGTTGAATTCCGACTAATAGACGAGTGTCCGATCGGGTCAACCGATCTCGGATGTTGCGTACCAGGACTACTGGGCTCGGGCCGAATCAGACACACGTATGCAACAGATACCGATAGGCGTCTTCCTAAGTAACAGCCGTAATCAATGGTGCCACAGATCTACTAATTACGGTGAAGATCATGGCCCACGACGCTGTACGGGTTTATAGCTGCCACAAACTTTAGGAAGTTTCAGCAATCGACGCGTAGTATGTGTGCTCAGACGGGTCGAGCATGCACTTGTGTATTAAGTTACTTGGCTGAACAACCTGTTGATAGATCTTGAGAGGACCGAGAAATTGCCCTCCGGTTATGAAACAGGTCCTGCGTACCAATCCTT")); //0
//        int n = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        String gene = scanner.nextLine();
//
//        int result = steadyGene(gene);
//        System.out.println(String.valueOf(result));
//
//        scanner.close();
    }
}
