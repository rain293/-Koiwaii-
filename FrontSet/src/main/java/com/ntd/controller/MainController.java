package com.ntd.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ntd.model.User;
import com.ntd.service.UserService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/*
 * RequestMapping は"/"を要求する
 * もしURLが"localhost:8080"or"localhost:8080/"　の場合は
 * @GetMappingのアノテーションを読んで行く
 */
@RequestMapping("/")
@Slf4j
/*
 * @Controller はマッピングしたあとの値をブラウザのほうに出していく
 */
@Controller
public class MainController {
	/*
	 * First Screen
	 * ここにlocalhost:8080を押したらマッピングして'login'を返す
	 * loginを返した後@Controllerを動いてtemplatesのファイルからloginというHTMLファイルを探して
	 * 終わったらブラウザに表示します
	 */
	
	private static  Map<Integer, String> selectYear = new LinkedHashMap<Integer, String>();
	private static  Map<Integer, String> selectMonth = new LinkedHashMap<Integer, String>();
	private static  Map<Integer, String> selectDay = new LinkedHashMap<Integer, String>();
	public static void setYearMonthDay() {
		 for(int i = 1990; i <= 2023; i++) {
		    	selectYear.put(i, Integer.toString(i));
		    }
			for(int i = 1; i <= 12; i++) {
				selectMonth.put(i, Integer.toString(i));
		 }
			for(int i = 1; i <= 31; i++) {
				selectDay.put(i, Integer.toString(i));
			}
	}
	@Resource
	UserService userService;
	
	/*
	 * login,home,register
	 * thymeleafから文字をもらってここでマッピングする
	 * もしマッピングがあっている場合HTML事返す
	 */
	/*
	 * return home page
	 */
	@GetMapping
	public String getHomeome1(Model model) {
		model.addAttribute("msg", "ようこそNTDへ!");
		model.addAttribute("log_in_out", "ログイン");
		return "home";
	}
	@GetMapping("/home")
	public String getHome2(Model model) {
		model.addAttribute("msg", "ようこそNTDへ!");
		model.addAttribute("log_in_out", "ログイン");
		return "home";
	}
	@PostMapping("/home")
	public String postHome(User user, Model model) {
		User newUser = userService.checkLogin(user);
		if(newUser == null) {
//			log.info("{}",newUser);
			model.addAttribute("msg", "ユーザー名とパスワードを正しく入力してください。");
			return "login";
		}else {
//			log.info(newUser.toString());
			model.addAttribute("msg", "ようこそNTDへ"+ newUser.getCustomerMei()+"様！");
			model.addAttribute("log_in_out", "ログアウト");
			return "home";
		}
	}
	
	/*
	 * return login page
	 */
	@GetMapping("/login")
	public  String getLogin(){
//		log.info("login is running");
		return "login";
	}
	
	@PostMapping("/login")
	public String postLogin() {
		return "login";
	}
	
	/*
	 * return register and check page
	 */
	@GetMapping("/register")
	public String register(Model model) {
		setYearMonthDay();
	    model.addAttribute("years",selectYear);
	    model.addAttribute("months",selectMonth);
	    model.addAttribute("days",selectDay);
		return "register";
	}
	
	@PostMapping("/check")
	public String registing(User user, Model model) {
		model.addAttribute("years",selectYear);
	    model.addAttribute("months",selectMonth);
	    model.addAttribute("days",selectDay);
	    log.info("{}",user);
	    /*
	     * データ確認
	     * 正しいの場合は新規登録をする
	     * 間違いの場合は新規登録の画面戻ってエラーメッセージを出す
	     */
	    if(isCorrect(user,model)) {
	    	return "check";
	    }
	    else {return "register";}
	}
	
	@GetMapping("check")
	public String getCheck() {
		return "check";
	}
	/*
	 * ここで新規登録のデータを確認する
	 */
	private boolean isCorrect(User user, Model model) {
		boolean isCheckBox = true;
		boolean isAllInput = true;
		boolean isAccountCorrect = true;
		boolean isPassCorrect = true;
		User u1 = userService.checkAccount(user);
		/*
		 * チェックボックスをチェックしてない場合
		 */
		if(user.getCheckBox() == null) {
			model.addAttribute("checkbox_error", "同意チェックボックスを✓してください。");
			isCheckBox = false;
		}
		/*
		 * データが全部入力していない場合
		 * エラーメッセージが表示される
		 * 戻り値はfalseに返す
		 */
		if(user.getCustomerMei() == "" || user.getCustomerSei() == "" ||
				user.getCustomerAccount() == "" || user.getCustomerPass() == "") {
				model.addAttribute("all_error", "全部入力してください。");
				isAllInput = false;
		}
		/*
		 * アカウントはデータベース上に存在する場合
		 */
		if(u1 != null ) {
			model.addAttribute("account_name_error", "アカウントは既に存在します。");
			isAccountCorrect = false;
		}
		/*
		 * パスワードは6以下の文字数しか入力していない場合
		 * エラーメッセージが表示される
		 * 戻り値はfalseに返す
		 */
		if(user.getCustomerPass().length()<6) {
			model.addAttribute("pass_error", "パスワードは最低6文字数は必要です。");
			isPassCorrect = false;
		}
		/*
		 * アカウント名が全角文字を入力される場合
		 */
		if(checkzenhan(user.getCustomerAccount()) == false) {
			model.addAttribute("account_name_error", "アカウントは半角英数字で入力してください。");
			isAccountCorrect = false;
		}
		/*
		 * パスワードが全角文字を入力される場合
		 */
		if(	checkzenhan(user.getCustomerPass()) == false) {
			model.addAttribute("pass_error", "パスワードは半角英数字で入力してください。");
			isPassCorrect = false;
		}
		/*
		 * すべての条件は正しいの場合
		 * データを揃えてデータベースに挿入する
		 */
		if(isCheckBox && isPassCorrect && isAccountCorrect && isAllInput) {
				String setupAge = user.getYear()+"-"+user.getMonth() +"-"+ user.getDay();
				user.setCustomerAge(setupAge);
				userService.insert(user);
				model.addAttribute("msg", "登録完了！");
				return true;
		}else return false;
	}
	/*
	 * ここで全角と半角を判定
	 * 半角だったらtrueに返す
	 * 全角だったらfalseに返す
	 */
	private boolean checkzenhan(String s) {
		 char[] chars = s.toCharArray();
		 boolean b = false;
		     for (int i = 0; i < chars.length; i++) {
		         if (String.valueOf(chars[i]).getBytes().length < 2) {
//		             System.out.print("半");
		             b = true;
		         } else {
//		             System.out.print("全");
		             b = false;
		         }
		     }
		   return b;
	}
}
