package com.sunshine.commons;

/**
 *
 * @author qiushengming
 * <p>java系统参数
 * </br>目前只定义了关键字符，没有定义出口方法，等用到的时候再写，太多了，有点累。</p>
 */
@SuppressWarnings("unused")
public final class SystemProperty {

/*用户的当前工作目录*/
private final static String USER_DIR = "user.dir";
/*用户的主目录*/
private final static String USER_HOME = "user.home";
/*行分隔符（在 UNIX 系统中是“/n”），换行符*/
private final static String LINE_SEPARATOR = "line.separator";
/*路径分隔符（在 UNIX 系统中是“:”）*/
private final static String PATH_SEPARATOR = "path.separator";
/*文件分隔符（在 UNIX 系统中是“/”）*/
private final static String FILE_SEPARATOR = "file.separator";
/*操作系统版本*/
private final static String OS_VERSION = "os.version";
/*操作系统的架构*/
private final static String OS_ARCH = "os.arch";
/*操作系统的名称*/
private final static String OS_NAME = "os.name";
/*一个或多个扩展目录的路径*/
private final static String JAVA_EXT_DIRS = "java.ext.dirs";
/*要使用的 JIT 编译器的名称*/
private final static String JAVA_COMPILER = "java.compiler";
/*默认的临时文件路径*/
private final static String JAVA_IO_TMPDIR = "java.io.tmpdir";
/*加载库时搜索的路径列表*/
private final static String JAVA_LIBRARY_PATH = "java.library.path";
/*Java 类路径*/
private final static String JAVA_CLASS_PATH = "java.class.path";
/*Java 类格式版本号*/
private final static String JAVA_CLASS_VERSION = "java.class.version";
/*Java 运行时环境规范名称*/
private final static String JAVA_SPECIFICATION_NAME = "java.specification.name";
/*Java 运行时环境规范供应商*/
private final static String JAVA_SPECIFICATION_VERDOR = "java.specification.vendor";
/*Java 运行时环境规范版本*/
private final static String JAVA_SPECIFICATION_VERSION ="java.specification.version";
/* Java 虚拟机实现名称*/
private final static String JAVA_VM_NAME = "java.vm.name";
/*Java 虚拟机实现供应商*/
private final static String JAVA_VM_VENDOR = "java.vm.vendor";
/*Java 虚拟机实现版本*/
private final static String JAVA_VM_VERSION = "java.vm.version";
/*Java 虚拟机规范名称*/
private final static String JAVA_VM_SPECIFICATION_NAME = "java.vm.specification.name";
/*Java 虚拟机规范供应商*/
private final static String JAVA_VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";
/*Java 虚拟机规范版本*/
private final static String JAVA_VM_SPECIFICATION_VERSION = "java.vm.specification.version";
/*Java 安装目录*/
private final static String JAVA_HOME = "java.home";
/*Java 供应商的 URL*/
private final static String JAVA_VENDOR_URL = "java.vendor.url";
/*Java 运行时环境供应商*/
private final static String JAVA_VENDOR = "java.vendor";
/*Java 运行时环境版本*/
private final static String JAVA_VERSION = "java.version";
/**
 *
 * 2017年3月31日
 * qiushengming
 * @return String
 * <p>用户当前工作目录</p>
 */
public static String getCurrentWorkingDirectory(){
return System.getProperty(USER_DIR);
}

/**
 *
 * 2017年3月31日
 * qiushengming
 * @return String
 * <p>用户当前主目录</p>
 */
public static String getHomeDirectory(){
return System.getProperty(USER_HOME);
}

/**
 *
 * 2017年3月31日
 * qiushengming
 * @return String
 * <p>换行符</p>
 */
public static String getLineSepatator(){
return System.getProperty(LINE_SEPARATOR);
}

public static void main(String[] args) {
System.out.println(getCurrentWorkingDirectory());
}
}
