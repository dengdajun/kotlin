// !WITH_NEW_INFERENCE
// !DIAGNOSTICS: -UNUSED_PARAMETER

fun String.invoke(i: Int) {}

fun foo(i: Int) {
    <!NI;DEBUG_INFO_MISSING_UNRESOLVED!><!NI;DEBUG_INFO_UNRESOLVED_WITH_TARGET, UNRESOLVED_REFERENCE_WRONG_RECEIVER!>i<!>(1)<!>

    <!FUNCTION_EXPECTED!>1<!>(1)
}