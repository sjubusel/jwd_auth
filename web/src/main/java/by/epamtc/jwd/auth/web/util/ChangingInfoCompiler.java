package by.epamtc.jwd.auth.web.util;

public class ChangingInfoCompiler {
    private static volatile ChangingInfoCompiler instance;

    private ChangingInfoCompiler() {
    }

    public static ChangingInfoCompiler getInstance() {
        ChangingInfoCompiler localInstance = instance;
        if (localInstance == null) {
            synchronized (ChangingInfoCompiler.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance
                            = new ChangingInfoCompiler();
                }
            }
        }
        return localInstance;
    }

}
