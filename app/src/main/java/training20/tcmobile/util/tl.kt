package training20.tcmobile.util

inline fun <T1, T2> ensureNotNull(p1: T1?, p2: T2?, f: (p1: T1, p2: T2) -> Unit) {
    if (p1 != null && p2 != null) f(p1, p2)
}

inline fun <T1, T2, T3> ensureNotNull(p1: T1?, p2: T2?, p3: T3?, f: (p1: T1, p2: T2, p3: T3) -> Unit) {
    if (p1 != null && p2 != null && p3 != null) f(p1, p2, p3)
}

inline fun <T1, T2, T3, T4> ensureNotNull(p1: T1?, p2: T2?, p3: T3?, p4: T4, f: (p1: T1, p2: T2, p3: T3, p4: T4) -> Unit) {
    if (p1 != null && p2 != null && p3 != null && p4 != null) f(p1, p2, p3, p4)
}

inline fun <T1, T2, U> applyNotNull(p1: T1?, p2: T2?, f: (p1: T1, p2: T2) -> U): U? {
    if (p1 != null && p2 != null) return f(p1, p2)
    return null
}

inline fun <T1, T2, T3, U> applyNotNull(p1: T1?, p2: T2?, p3: T3?, f: (p1: T1, p2: T2, p3: T3) -> U): U? {
    if (p1 != null && p2 != null && p3 != null) return f(p1, p2, p3)
    return null
}

inline fun <T1, T2, T3, T4, U> applyNotNull(p1: T1?, p2: T2?, p3: T3?, p4: T4, f: (p1: T1, p2: T2, p3: T3, p4: T4) -> U): U? {
    if (p1 != null && p2 != null && p3 != null && p4 != null) return f(p1, p2, p3, p4)
    return null
}