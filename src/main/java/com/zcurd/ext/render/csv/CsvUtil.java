//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.ext.render.csv;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class CsvUtil {
    private static UserSettings userSettings = new UserSettings();

    private CsvUtil() {
    }

    public static String createCSV(List headers, List data, List columns) {
        StringBuffer strOut = new StringBuffer("");
        if (headers != null && !headers.isEmpty()) {
            listToCSV(strOut, headers);
        }

        if (data != null && !data.isEmpty()) {
            Object obj;
            for(Iterator itr = data.iterator(); itr.hasNext(); obj = null) {
                obj = itr.next();
                Class cls = obj.getClass();
                if (cls != null && cls.isArray()) {
                    if (obj != null) {
                        Object[] objs = (Object[])obj;
                        if (objs != null) {
                            for(short i = 0; i < objs.length; ++i) {
                                createCol(strOut, objs[i]);
                                strOut.append(",");
                            }

                            strOut = strOut.deleteCharAt(strOut.length() - 1);
                            strOut.append("\n");
                        }
                    }
                } else {
                    int i;
                    if (obj instanceof List) {
                        List objlist = (List)obj;
                        if (columns != null && !columns.isEmpty()) {
                            for(i = 0; i < columns.size(); ++i) {
                                createCol(strOut, objlist.get((Integer)columns.get(i)));
                                strOut.append(",");
                            }

                            strOut = strOut.deleteCharAt(strOut.length() - 1);
                            strOut.append("\n");
                        } else {
                            listToCSV(strOut, objlist);
                        }
                    } else {
                        Set entries;
                        Iterator var21;
                        if (obj instanceof Map) {
                            Map objmap = (Map)obj;
                            if (columns != null && !columns.isEmpty()) {
                                for(i = 0; i < columns.size(); ++i) {
                                    createCol(strOut, objmap.get(columns.get(i)));
                                    strOut.append(",");
                                }

                                strOut = strOut.deleteCharAt(strOut.length() - 1);
                                strOut.append("\n");
                            } else {
                                entries = objmap.keySet();
                                var21 = entries.iterator();

                                while(var21.hasNext()) {
                                    Object key = var21.next();
                                    createCol(strOut, objmap.get(key));
                                    strOut.append(",");
                                }

                                strOut = strOut.deleteCharAt(strOut.length() - 1);
                                strOut.append("\n");
                            }
                        } else if (obj instanceof Model) {
                            Model objmodel = (Model)obj;
                            if (columns != null && !columns.isEmpty()) {
                                for(i = 0; i < columns.size(); ++i) {
                                    createCol(strOut, objmodel.get("" + columns.get(i)));
                                    strOut.append(",");
                                }

                                strOut = strOut.deleteCharAt(strOut.length() - 1);
                                strOut.append("\n");
                            } else {
                                entries = objmodel._getAttrsEntrySet();
                                var21 = entries.iterator();

                                while(var21.hasNext()) {
                                    Entry entry = (Entry)var21.next();
                                    createCol(strOut, entry.getValue());
                                    strOut.append(",");
                                }

                                strOut = strOut.deleteCharAt(strOut.length() - 1);
                                strOut.append("\n");
                            }
                        } else if (!(obj instanceof Record)) {
                            while(itr.hasNext()) {
                                Object objs = itr.next();
                                if (objs != null) {
                                    createCol(strOut, objs);
                                    strOut.append("\n");
                                }
                            }
                        } else {
                            Record objrecord = (Record)obj;
                            Map<String, Object> map = objrecord.getColumns();
                            if (columns != null && !columns.isEmpty()) {
                                for(int j = 0; j < columns.size(); ++j) {
                                    createCol(strOut, objrecord.get("" + columns.get(j)));
                                    strOut.append(",");
                                }

                                strOut = strOut.deleteCharAt(strOut.length() - 1);
                                strOut.append("\n");
                            } else {
                                Set<String> keys = map.keySet();
                                Iterator var11 = keys.iterator();

                                while(var11.hasNext()) {
                                    String key = (String)var11.next();
                                    createCol(strOut, objrecord.get(key));
                                    strOut.append(",");
                                }

                                strOut = strOut.deleteCharAt(strOut.length() - 1);
                                strOut.append("\n");
                            }
                        }
                    }
                }
            }

            return strOut.toString();
        } else {
            return strOut.toString();
        }
    }

    public static void listToCSV(StringBuffer strOut, List<?> list) {
        if (list != null && !list.isEmpty()) {
            for(short i = 0; i < list.size(); ++i) {
                createCol(strOut, list.get(i));
                strOut.append(",");
            }

            strOut = strOut.deleteCharAt(strOut.length() - 1);
            strOut.append("\n");
        }

    }

    public static void createCol(StringBuffer strOut, Object obj) {
        if (obj != null) {
            strOut.append("\"");
            String content = null;
            if (obj instanceof Boolean) {
                content = ((Boolean)obj).toString();
            } else if (obj instanceof Calendar) {
                content = ((Calendar)obj).toString();
            } else if (obj instanceof Timestamp) {
                content = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date(((Timestamp)obj).getTime()));
            } else if (obj instanceof Date) {
                content = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format((Date)obj);
            } else {
                content = write(String.valueOf(obj));
            }

            strOut.append(content);
            strOut.append("\"");
        } else {
            strOut.append("\" \" ");
        }

    }

    public static String write(String content) {
        boolean textQualify = userSettings.forceQualifier;
        if (content.length() > 0) {
            content = content.trim();
        }

        if (!textQualify && userSettings.useTextQualifier && (content.indexOf(userSettings.textQualifier) > -1 || content.indexOf(userSettings.delimiter) > -1 || content.indexOf(10) > -1 || content.indexOf(13) > -1 || content.indexOf(userSettings.recordDelimiter) > -1 || content.length() > 0 && content.charAt(0) == userSettings.comment || content.length() == 0)) {
            textQualify = true;
        }

        if (userSettings.useTextQualifier && !textQualify && content.length() > 0) {
            char firstLetter = content.charAt(0);
            if (firstLetter == ' ' || firstLetter == '\t') {
                textQualify = true;
            }

            if (!textQualify && content.length() > 1) {
                char lastLetter = content.charAt(content.length() - 1);
                if (lastLetter == ' ' || lastLetter == '\t') {
                    textQualify = true;
                }
            }
        }

        if (textQualify) {
            if (userSettings.escapeMode == 2) {
                content = replace(content, "\\", "\\\\");
                content = replace(content, "" + userSettings.textQualifier, "\\" + userSettings.textQualifier);
            } else {
                content = replace(content, "" + userSettings.textQualifier, "" + userSettings.textQualifier + userSettings.textQualifier);
            }
        } else if (userSettings.escapeMode == 2) {
            content = replace(content, "\\", "\\\\");
            content = replace(content, "" + userSettings.delimiter, "\\" + userSettings.delimiter);
            content = replace(content, "\r", "\\\r");
            content = replace(content, "\n", "\\\n");
        }

        return content;
    }

    public static String replace(String original, String pattern, String replace) {
        int len = pattern.length();
        int found = original.indexOf(pattern);
        if (found <= -1) {
            return original;
        } else {
            StringBuffer sb = new StringBuffer();

            int start;
            for(start = 0; found != -1; found = original.indexOf(pattern, start)) {
                sb.append(original.substring(start, found));
                sb.append(replace);
                start = found + len;
            }

            sb.append(original.substring(start));
            return sb.toString();
        }
    }
}
