/*     */ package org.wltea.analyzer.cfg;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.InvalidPropertiesFormatException;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class Configuration
/*     */ {
/*     */   private static final String FILE_NAME = "IKAnalyzer.cfg.xml";
/*     */   private static final String EXT_DICT = "ext_dict";
/*     */   private static final String EXT_STOP = "ext_stopwords";
/*     */   private Properties props;
/*     */   private boolean useSmart;
/*     */ 
/*     */   public Configuration()
/*     */   {
/*  60 */     this.props = new Properties();
/*     */ 
/*  62 */     InputStream input = getClass().getClassLoader().getResourceAsStream("IKAnalyzer.cfg.xml");
/*  63 */     if (input != null)
/*     */       try {
/*  65 */         this.props.loadFromXML(input);
/*     */       } catch (InvalidPropertiesFormatException e) {
/*  67 */         e.printStackTrace();
/*     */       } catch (IOException e) {
/*  69 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public boolean useSmart()
/*     */   {
/*  80 */     return this.useSmart;
/*     */   }
/*     */ 
/*     */   public void setUseSmart(boolean useSmart)
/*     */   {
/*  89 */     this.useSmart = useSmart;
/*     */   }
/*     */ 
/*     */   public List<String> getExtDictionarys()
/*     */   {
/*  97 */     List extDictFiles = new ArrayList(2);
/*  98 */     String extDictCfg = this.props.getProperty("ext_dict");
/*  99 */     if (extDictCfg != null)
/*     */     {
/* 101 */       String[] filePaths = extDictCfg.split(";");
/* 102 */       if (filePaths != null) {
/* 103 */         for (String filePath : filePaths) {
/* 104 */           if ((filePath != null) && (!"".equals(filePath.trim()))) {
/* 105 */             extDictFiles.add(filePath.trim());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 110 */     return extDictFiles;
/*     */   }
/*     */ 
/*     */   public List<String> getExtStopWordDictionarys()
/*     */   {
/* 119 */     List extStopWordDictFiles = new ArrayList(2);
/* 120 */     String extStopWordDictCfg = this.props.getProperty("ext_stopwords");
/* 121 */     if (extStopWordDictCfg != null)
/*     */     {
/* 123 */       String[] filePaths = extStopWordDictCfg.split(";");
/* 124 */       if (filePaths != null) {
/* 125 */         for (String filePath : filePaths) {
/* 126 */           if ((filePath != null) && (!"".equals(filePath.trim()))) {
/* 127 */             extStopWordDictFiles.add(filePath.trim());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 132 */     return extStopWordDictFiles;
/*     */   }
/*     */ }

/* Location:           E:\APK反编译\jd-gui-0.3.5.windows\IKAnalyzer2013.jar
 * Qualified Name:     org.wltea.analyzer.cfg.Configuration
 * JD-Core Version:    0.6.2
 */