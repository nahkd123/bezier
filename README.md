# nahkd's Bézier curve library
Tiny little Bézier curve library implemented in Java.

There are 2 classes so I just call em' tiny. The `Bezier` class is for creating parametric Bézier curve, and the `BezierFunction` is for converting that parametric into `f(x)` function (put x to get y).

`BezierFunction` implements `DoubleUnaryOperator`, so you can plug your Bézier function inside whatever is accepting `DoubleUnaryOperator`.

## License
MIT license.