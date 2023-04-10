import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Scanner;

public class NaverNewsSearchMain {

	public static void main(String[] args) { //여기가 main 메소드
		System.out.print("검색어 입력 : ");
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		System.out.println(NaverNewsRun.getInstance().searchNews(text));
		//메인 메소드에서는 검색어를 받고 블로그서치 메소드를 실행시키는 역할만 함.
	}
}
