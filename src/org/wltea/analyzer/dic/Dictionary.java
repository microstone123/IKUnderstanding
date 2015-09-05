/*     */ package org.wltea.analyzer.dic;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.Collection;
/*     */ import java.util.List;

import org.wltea.analyzer.cfg.Configuration;
/*     */ 
/*     */ 
/*     */ public class Dictionary
/*     */ {
/*     */   private static final String PATH_DIC_MAIN = "org/wltea/analyzer/dic/main2012.dic";
/*     */   private static final String PATH_DIC_QUANTIFIER = "org/wltea/analyzer/dic/quantifier.dic";
/*     */   private static Dictionary singleton;
/*     */   private DictSegment _MainDict;
/*     */   private DictSegment _StopWordDict;
/*     */   private DictSegment _QuantifierDict;
/*     */   private Configuration cfg;
/*     */ 
/*     */   private Dictionary(Configuration cfg)
/*     */   {
/*  73 */     this.cfg = cfg;
/*  74 */     loadMainDict();
/*  75 */     loadStopWordDict();
/*  76 */     loadQuantifierDict();
/*     */   }
/*     */ 
/*     */   public static Dictionary getInstance(Configuration cfg)
/*     */   {
/*  88 */     if (singleton == null) {
/*  89 */       synchronized (Dictionary.class) {
/*  90 */         if (singleton == null) {
/*  91 */           singleton = new Dictionary(cfg);
/*     */         }
/*     */       }
/*     */     }
/*  95 */     return singleton;
/*     */   }
/*     */ 
/*     */   public static void addWords(Collection<String> words)
/*     */   {
/* 103 */     if (words != null)
/* 104 */       for (String word : words)
/* 105 */         if (word != null)
/*     */         {
/* 107 */           singleton._MainDict.fillSegment(word.trim().toLowerCase().toCharArray());
/*     */         }
/*     */   }
/*     */ 
/*     */   public static void disableWords(Collection<String> words)
/*     */   {
/* 118 */     if (words != null)
/* 119 */       for (String word : words)
/* 120 */         if (word != null)
/*     */         {
/* 122 */           singleton._MainDict.disableSegment(word.trim().toLowerCase().toCharArray());
/*     */         }
/*     */   }
/*     */ 
/*     */   public static Hit matchInMainDict(char[] charArray)
/*     */   {
/* 134 */     return singleton._MainDict.match(charArray);
/*     */   }
/*     */ 
/*     */   public static Hit matchInMainDict(char[] charArray, int begin, int length)
/*     */   {
/* 145 */     return singleton._MainDict.match(charArray, begin, length);
/*     */   }
/*     */ 
/*     */   public static Hit matchInQuantifierDict(char[] charArray, int begin, int length)
/*     */   {
/* 156 */     return singleton._QuantifierDict.match(charArray, begin, length);
/*     */   }
/*     */ 
/*     */   public static Hit matchWithHit(char[] charArray, int currentIndex, Hit matchedHit)
/*     */   {
/* 168 */     DictSegment ds = matchedHit.getMatchedDictSegment();
/* 169 */     return ds.match(charArray, currentIndex, 1, matchedHit);
/*     */   }
/*     */ 
/*     */   public static boolean isStopWord(char[] charArray, int begin, int length)
/*     */   {
/* 181 */     return singleton._StopWordDict.match(charArray, begin, length).isMatch();
/*     */   }
/*     */ 
/*     */   private void loadMainDict()
/*     */   {
/* 189 */     this._MainDict = new DictSegment(Character.valueOf('\000'));
/*     */ 
/* 191 */     InputStream is = getClass().getClassLoader().getResourceAsStream("org/wltea/analyzer/dic/main2012.dic");
/* 192 */     if (is == null) {
/* 193 */       throw new RuntimeException("Main Dictionary not found!!!");
/*     */     }
/*     */     try
/*     */     {
/* 197 */       BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
/* 198 */       String theWord = null;
/*     */       do {
/* 200 */         theWord = br.readLine();
/* 201 */         if ((theWord != null) && (!"".equals(theWord.trim())))
/* 202 */           this._MainDict.fillSegment(theWord.trim().toLowerCase().toCharArray());
/*     */       }
/* 204 */       while (theWord != null);
/*     */     }
/*     */     catch (IOException ioe) {
/* 207 */       System.err.println("Main Dictionary loading exception.");
/* 208 */       ioe.printStackTrace();
/*     */       try
/*     */       {
/* 212 */         if (is != null) {
/* 213 */           is.close();
/* 214 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 217 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 212 */         if (is != null) {
/* 213 */           is.close();
/* 214 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 217 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 221 */     loadExtDict();
/*     */   }
/*     */ 
/*     */   private void loadExtDict()
/*     */   {
/* 229 */     List<String> extDictFiles = this.cfg.getExtDictionarys();
/* 230 */     if (extDictFiles != null) {
/* 231 */       InputStream is = null;
/* 232 */       for (String extDictName : extDictFiles)
/*     */       {
/* 234 */         System.out.println("加载扩展词典：" + extDictName);
/* 235 */         is = getClass().getClassLoader().getResourceAsStream(extDictName);
/*     */ 
/* 237 */         if (is != null)
/*     */         {
/*     */           try
/*     */           {
/* 241 */             BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
/* 242 */             String theWord = null;
/*     */             do {
/* 244 */               theWord = br.readLine();
/* 245 */               if ((theWord != null) && (!"".equals(theWord.trim())))
/*     */               {
/* 248 */                 this._MainDict.fillSegment(theWord.trim().toLowerCase().toCharArray());
/*     */               }
/*     */             }
/* 250 */             while (theWord != null);
/*     */           }
/*     */           catch (IOException ioe) {
/* 253 */             System.err.println("Extension Dictionary loading exception.");
/* 254 */             ioe.printStackTrace();
/*     */             try
/*     */             {
/* 258 */               if (is != null) {
/* 259 */                 is.close();
/* 260 */                 is = null;
/*     */               }
/*     */             } catch (IOException e) {
/* 263 */               e.printStackTrace();
/*     */             }
/*     */           }
/*     */           finally
/*     */           {
/*     */             try
/*     */             {
/* 258 */               if (is != null) {
/* 259 */                 is.close();
/* 260 */                 is = null;
/*     */               }
/*     */             } catch (IOException e) {
/* 263 */               e.printStackTrace();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void loadStopWordDict()
/*     */   {
/* 275 */     this._StopWordDict = new DictSegment(Character.valueOf('\000'));
/*     */ 
/* 277 */     List<String> extStopWordDictFiles = this.cfg.getExtStopWordDictionarys();
/* 278 */     if (extStopWordDictFiles != null) {
/* 279 */       InputStream is = null;
/* 280 */       for (String extStopWordDictName : extStopWordDictFiles) {
/* 281 */         System.out.println("加载扩展停止词典：" + extStopWordDictName);
/*     */ 
/* 283 */         is = getClass().getClassLoader().getResourceAsStream(extStopWordDictName);
/*     */ 
/* 285 */         if (is != null)
/*     */         {
/*     */           try
/*     */           {
/* 289 */             BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
/* 290 */             String theWord = null;
/*     */             do {
/* 292 */               theWord = br.readLine();
/* 293 */               if ((theWord != null) && (!"".equals(theWord.trim())))
/*     */               {
/* 296 */                 this._StopWordDict.fillSegment(theWord.trim().toLowerCase().toCharArray());
/*     */               }
/*     */             }
/* 298 */             while (theWord != null);
/*     */           }
/*     */           catch (IOException ioe) {
/* 301 */             System.err.println("Extension Stop word Dictionary loading exception.");
/* 302 */             ioe.printStackTrace();
/*     */             try
/*     */             {
/* 306 */               if (is != null) {
/* 307 */                 is.close();
/* 308 */                 is = null;
/*     */               }
/*     */             } catch (IOException e) {
/* 311 */               e.printStackTrace();
/*     */             }
/*     */           }
/*     */           finally
/*     */           {
/*     */             try
/*     */             {
/* 306 */               if (is != null) {
/* 307 */                 is.close();
/* 308 */                 is = null;
/*     */               }
/*     */             } catch (IOException e) {
/* 311 */               e.printStackTrace();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void loadQuantifierDict()
/*     */   {
/* 323 */     this._QuantifierDict = new DictSegment(Character.valueOf('\000'));
/*     */ 
/* 325 */     InputStream is = getClass().getClassLoader().getResourceAsStream("org/wltea/analyzer/dic/quantifier.dic");
/* 326 */     if (is == null)
/* 327 */       throw new RuntimeException("Quantifier Dictionary not found!!!");
/*     */     try
/*     */     {
/* 330 */       BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
/* 331 */       String theWord = null;
/*     */       do {
/* 333 */         theWord = br.readLine();
/* 334 */         if ((theWord != null) && (!"".equals(theWord.trim())))
/* 335 */           this._QuantifierDict.fillSegment(theWord.trim().toCharArray());
/*     */       }
/* 337 */       while (theWord != null);
/*     */     }
/*     */     catch (IOException ioe) {
/* 340 */       System.err.println("Quantifier Dictionary loading exception.");
/* 341 */       ioe.printStackTrace();
/*     */       try
/*     */       {
/* 345 */         if (is != null) {
/* 346 */           is.close();
/* 347 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 350 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 345 */         if (is != null) {
/* 346 */           is.close();
/* 347 */           is = null;
/*     */         }
/*     */       } catch (IOException e) {
/* 350 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\APK反编译\jd-gui-0.3.5.windows\IKAnalyzer2013.jar
 * Qualified Name:     org.wltea.analyzer.dic.Dictionary
 * JD-Core Version:    0.6.2
 */