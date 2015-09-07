package com.example.ikandroid;

import java.io.IOException;
import java.io.StringReader;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class IKAnalyzerHelper {

	public static final String IKANALY_SEPARATOR = "|";

	public static String getAnalyWord(String word) {
		String s = "";
		try {
			StringReader re = new StringReader(word);
			IKSegmenter ik = new IKSegmenter(re, true);
			Lexeme lex = null;
			while ((lex = ik.next()) != null) {
				s = s + lex.getLexemeText() + IKANALY_SEPARATOR;
			}
			if (s.endsWith(IKANALY_SEPARATOR)) {
				s = s.substring(0, s.length() - 1);
			}
			re.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			s = null;
		}
		return s;
	}
}
