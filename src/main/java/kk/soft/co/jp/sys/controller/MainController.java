package kk.soft.co.jp.sys.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kk.soft.co.jp.sys.mapper.MessageInfoMstMapper;
import kk.soft.co.jp.sys.model.MessageInfoMstModel;
import kk.soft.co.jp.sys.service.MessageInfoMstService;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/")
@Slf4j
@Controller

public class MainController {
	private static Map<Integer, String> selectYear = new LinkedHashMap<Integer, String>();
	private static Map<Integer, String> selectMonth = new LinkedHashMap<Integer, String>();
	private static Map<Integer, String> selectDay = new LinkedHashMap<Integer, String>();

	public static void setYearMonthDay() {
		for (int i = 1990; i <= 2023; i++) {
			selectYear.put(i, Integer.toString(i));
		}
		for (int i = 1; i <= 12; i++) {
			selectMonth.put(i, Integer.toString(i));
		}
		for (int i = 1; i <= 31; i++) {
			selectDay.put(i, Integer.toString(i));
		}

	}

	@Autowired
	private MessageInfoMstMapper messageInfoMstMapper;
	@GetMapping("/list")
	public String list(Model model) {
		List<MessageInfoMstModel> messageInfoMstModel = messageInfoMstMapper.selectAll();
		model.addAttribute("messageInfoMstModel", messageInfoMstModel);
		return "list";
	}
	@GetMapping("/messageInfoMstModel/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
		MessageInfoMstModel messageInfoMstModel = messageInfoMstMapper.findbyid(id);
        model.addAttribute("messageInfoMstModel", messageInfoMstModel);
        return "findbyid";
    }
	
	@GetMapping("/messageInfoMstModel/{name}")
    public String showUser2(@PathVariable("name") int name, Model model) {
		MessageInfoMstModel messageInfoMstModel = messageInfoMstMapper.findbyname(name);
        model.addAttribute("messageInfoMstModel", messageInfoMstModel);
        return "findbyname";
    }
	@Resource
	MessageInfoMstService messageInfoMstService;
	/*
	 * home
	 */
	@GetMapping
	public String getHome1(Model model) {
		model.addAttribute("msg", "ようこそ!");
		model.addAttribute("log_in_out", "ログイン");
		return "home";
	}
	@GetMapping("/home")
	public String getHome2(Model model) {
		model.addAttribute("msg", "ようこそ!");
		model.addAttribute("log_in_out", "ログイン");
		return "home";
	}
	@PostMapping("/home")
	public String postHome(MessageInfoMstModel messageInfoMstModel, Model model) {
		MessageInfoMstModel newMessageInfoMstModel = messageInfoMstService.checkSignin(messageInfoMstModel);
		if (newMessageInfoMstModel == null) {
			model.addAttribute("msg", "ユーザー名とパスワードを正しく入力してください。");
			return "signin";
		} else {
			model.addAttribute("msg", "ようこそ! ");
			model.addAttribute("msg1", newMessageInfoMstModel.getName() + " 様");
			model.addAttribute("log_in_out", "ログアウト");
			return "home";
		}
	}

	/*
	 * signin
	 */
	@GetMapping("/signin")
	public String getSignin() {
		return "signin";
	}

	@PostMapping("/signin")
	public String postSignin() {
		return "signin";
	}

	/*
	 * signup
	 */
	@GetMapping("/signup")
	public String signup(Model model) {
		setYearMonthDay();
		model.addAttribute("years", selectYear);
		model.addAttribute("months", selectMonth);
		model.addAttribute("days", selectDay);
		return "signup";
	}

	@PostMapping("/check")
	public String checking(MessageInfoMstModel messageInfoMstModel, Model model) {
		model.addAttribute("years", selectYear);
		model.addAttribute("months", selectMonth);
		model.addAttribute("days", selectDay);
		log.info("{}", messageInfoMstModel);
		if (isCorrect(messageInfoMstModel, model)) {
			return "check";
		} else {
			return "signup";
		}
	}

	@GetMapping("check")
	public String checked() {
		return "check";
	}

	private boolean isCorrect(MessageInfoMstModel messageInfoMstModel, Model model) {
		boolean isPrivacy = true;
		boolean isAllInput = true;
		boolean isEmailCorrect = true;
		boolean isPassCorrect = true;

		MessageInfoMstModel ok = messageInfoMstService.checkEmail(messageInfoMstModel);
		/*
		 * チェックボックスをチェックしてない場合
		 */
		if (messageInfoMstModel.getPrivacy() == null) {
			model.addAttribute("privacy_error", "TOEを同意してください。");
			isPrivacy = false;
		}
		/*
		 * データが全部入力していない場合
		 * エラーメッセージが表示される
		 * 戻り値はfalseに返す
		 */
		if (messageInfoMstModel.getEmail() == "" || messageInfoMstModel.getPassword() == "") {
			model.addAttribute("all_error", "全部入力してください。");
			isAllInput = false;
		}

		/*
		 * アカウントはデータベース上に存在する場合
		 */
		if (ok != null) {
			model.addAttribute("email_error", "メールを正しく入力して下さい");
			isEmailCorrect = false;
		}
		/*
		 * パスワードは8以下の文字数しか入力していない場合
		 * エラーメッセージが表示される
		 * 戻り値はfalseに返す
		 */
		if (messageInfoMstModel.getPassword().length() < 6) {
			model.addAttribute("password_error", "パスワードは最低6文字数は必要です。");
			isPassCorrect = false;
		}
		/*
		 * アカウント名が全角文字を入力される場合
		 */
		if (checkzenhan(messageInfoMstModel.getEmail()) == false) {
			model.addAttribute("email_error", "メールを正しく入力して下さい");
			isEmailCorrect = false;
		}
		/*
		 * パスワードが全角文字を入力される場合
		 */
		if (checkzenhan(messageInfoMstModel.getPassword()) == false) {
			model.addAttribute("pass_error", "パスワードは半角英数字で入力してください。");
			isPassCorrect = false;
		}
		/*
		 * すべての条件は正しいの場合
		 * データを揃えてデータベースに挿入する
		 */
		if (isPrivacy && isPassCorrect && isEmailCorrect && isAllInput) {
			messageInfoMstService.insert(messageInfoMstModel);
			model.addAttribute("msg", "登録完了！");
			return true;
		} else
			return false;
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
				//			             System.out.print("半");
				b = true;
			} else {
				//			             System.out.print("全");
				b = false;
			}
		}
		return b;

	}
}
