@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")
//@file:JsModule("paper")
package bz.stew.bracken.ui.extension

import kotlin.js.*
import kotlin.js.Json
import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.workers.*
import org.w3c.xhr.*

external var version: String = definedExternally
external object settings {
    var applyMatrix: Boolean = definedExternally
    var handleSize: Number = definedExternally
    var hitTolerance: Number = definedExternally
}
external var paper: PaperScope = definedExternally
external var project: Project = definedExternally
external var projects: Array<Project> = definedExternally
external var view: View = definedExternally
external var tool: Tool = definedExternally
external var tools: Array<Tool> = definedExternally
external fun install(scope: Any): Unit = definedExternally
external fun setup(canvas: String): Unit = definedExternally
external fun setup(canvas: HTMLCanvasElement): Unit = definedExternally
external fun activate(): Unit = definedExternally
external open class Matrix(a: Number, c: Number, b: Number, d: Number, tx: Number, ty: Number) {
    open var a: Number = definedExternally
    open var c: Number = definedExternally
    open var b: Number = definedExternally
    open var d: Number = definedExternally
    open var tx: Number = definedExternally
    open var ty: Number = definedExternally
    open var values: Number = definedExternally
    open var translation: Point = definedExternally
    open var scaling: Point = definedExternally
    open var rotation: Number = definedExternally
    open fun set(a: Number, c: Number, b: Number, d: Number, tx: Number, ty: Number): Matrix = definedExternally
    open fun clone(): Matrix = definedExternally
    open fun equals(matrix: Matrix): Boolean = definedExternally
    override fun toString(): String = definedExternally
    open fun reset(): Unit = definedExternally
    open fun apply(): Boolean = definedExternally
    open fun translate(point: Point): Matrix = definedExternally
    open fun translate(dx: Number, dy: Number): Matrix = definedExternally
    open fun scale(scale: Number, center: Point? = definedExternally /* null */): Matrix = definedExternally
    open fun scale(hor: Number, ver: Number, center: Point? = definedExternally /* null */): Matrix = definedExternally
    open fun rotate(angle: Number, center: Point): Matrix = definedExternally
    open fun rotate(angle: Number, x: Number, y: Number): Matrix = definedExternally
    open fun shear(shear: Point, center: Point? = definedExternally /* null */): Matrix = definedExternally
    open fun shear(hor: Number, ver: Number, center: Point? = definedExternally /* null */): Matrix = definedExternally
    open fun skew(skew: Point, center: Point? = definedExternally /* null */): Matrix = definedExternally
    open fun skew(hor: Number, ver: Number, center: Point? = definedExternally /* null */): Matrix = definedExternally
    open fun concatenate(mx: Matrix): Matrix = definedExternally
    open fun preConcatenate(mx: Matrix): Matrix = definedExternally
    open fun chain(mx: Matrix): Matrix = definedExternally
    open fun isIdentity(): Boolean = definedExternally
    open fun isInvertible(): Boolean = definedExternally
    open fun isSingular(): Boolean = definedExternally
    open fun transform(point: Point): Point = definedExternally
    open fun transform(src: Array<Number>, dst: Array<Number>, count: Number): Array<Number> = definedExternally
    open fun inverseTransform(point: Point): Point = definedExternally
    open fun decompose(): Any = definedExternally
    open fun inverted(): Matrix = definedExternally
    open fun applyToContext(ctx: CanvasRenderingContext2D): Unit = definedExternally
}
external open class Point {
    constructor(x: Number, y: Number)
    constructor(values: Array<Number>)
    constructor(`object`: Any)
    constructor(size: Size)
    constructor(point: Point)
    open var x: Number = definedExternally
    open var y: Number = definedExternally
    open var length: Number = definedExternally
    open var angle: Number = definedExternally
    open var angleInRadians: Number = definedExternally
    open var quadrant: Number = definedExternally
    open var selected: Boolean = definedExternally
    open fun equals(point: Point): Boolean = definedExternally
    open fun clone(): Point = definedExternally
    override fun toString(): String = definedExternally
    open fun getAngle(Point: Point): Number = definedExternally
    open fun getAngleInRadians(point: Point): Number = definedExternally
    open fun getDirectedAngle(point: Point): Number = definedExternally
    open fun getDistance(point: Point, squared: Boolean? = definedExternally /* null */): Number = definedExternally
    open fun normalize(length: Number? = definedExternally /* null */): Point = definedExternally
    open fun rotate(angle: Number, center: Point? = definedExternally /* null */): Point = definedExternally
    open fun transform(matrix: Matrix): Point = definedExternally
    open fun isInside(rect: Rectangle): Boolean = definedExternally
    open fun isClose(point: Point, tolerance: Number): Boolean = definedExternally
    open fun isColinear(point: Point): Boolean = definedExternally
    open fun isOrthogonal(point: Point): Boolean = definedExternally
    open fun isZero(): Boolean = definedExternally
    open fun isNan(): Boolean = definedExternally
    open fun dot(point: Point): Number = definedExternally
    open fun cross(point: Point): Number = definedExternally
    open fun project(point: Point): Point = definedExternally
    open fun round(): Point = definedExternally
    open fun ceil(): Point = definedExternally
    open fun floor(): Point = definedExternally
    open fun abs(): Point = definedExternally
    open fun add(point: Point): Point = definedExternally
    open fun add(point: Array<Number>): Point = definedExternally
    open fun subtract(point: Point): Point = definedExternally
    open fun subtract(point: Array<Number>): Point = definedExternally
    open fun multiply(point: Point): Point = definedExternally
    open fun multiply(point: Array<Number>): Point = definedExternally
    open fun multiply(point: Number): Point = definedExternally
    open fun divide(point: Point): Point = definedExternally
    open fun divide(point: Array<Number>): Point = definedExternally
    open fun divide(point: Number): Point = definedExternally
    companion object {
        fun min(point1: Point, point2: Point): Point = definedExternally
        fun max(point1: Point, point2: Point): Point = definedExternally
        fun random(): Point = definedExternally
    }
}
external open class Rectangle {
    constructor(point: Point, size: Size)
    constructor(x: Number, y: Number, width: Number, height: Number)
    constructor(`object`: Any)
    constructor(from: Point, to: Point)
    constructor(rt: Rectangle)
    open var x: Number = definedExternally
    open var y: Number = definedExternally
    open var width: Number = definedExternally
    open var height: Number = definedExternally
    open var point: Point = definedExternally
    open var size: Size = definedExternally
    open var left: Number = definedExternally
    open var top: Number = definedExternally
    open var right: Number = definedExternally
    open var bottom: Number = definedExternally
    open var center: Point = definedExternally
    open var topLeft: Point = definedExternally
    open var topRight: Point = definedExternally
    open var bottomLeft: Point = definedExternally
    open var bottomRight: Point = definedExternally
    open var leftCenter: Point = definedExternally
    open var topCenter: Point = definedExternally
    open var rightCenter: Point = definedExternally
    open var bottomCenter: Point = definedExternally
    open var area: Number = definedExternally
    open var selected: Boolean = definedExternally
    open fun clone(): Rectangle = definedExternally
    open fun equals(rect: Rectangle): Boolean = definedExternally
    override fun toString(): String = definedExternally
    open fun isEmpty(): Boolean = definedExternally
    open fun contains(point: Point): Boolean = definedExternally
    open fun contains(rect: Rectangle): Boolean = definedExternally
    open fun intersects(rect: Rectangle): Boolean = definedExternally
    open fun intersect(rect: Rectangle): Rectangle = definedExternally
    open fun unite(rect: Rectangle): Rectangle = definedExternally
    open fun include(point: Point): Rectangle = definedExternally
    open fun expand(amount: Number): Rectangle = definedExternally
    open fun expand(amount: Point): Rectangle = definedExternally
    open fun expand(amount: Size): Rectangle = definedExternally
    open fun expand(hor: Number, ver: Number): Rectangle = definedExternally
    open fun scale(amount: Number): Rectangle = definedExternally
    open fun scale(hor: Number, ver: Number): Rectangle = definedExternally
}
external open class Size {
    constructor(width: Number, height: Number)
    constructor(array: Array<Number>)
    constructor(`object`: Any)
    constructor(size: Size)
    constructor(point: Point)
    open var width: Number = definedExternally
    open var height: Number = definedExternally
    open fun equals(): Boolean = definedExternally
    open fun clone(): Size = definedExternally
    override fun toString(): String = definedExternally
    open fun isZero(): Boolean = definedExternally
    open fun isNan(): Boolean = definedExternally
    open fun round(): Size = definedExternally
    open fun ceil(): Size = definedExternally
    open fun floor(): Size = definedExternally
    open fun abs(): Size = definedExternally
    open fun add(size: Size): Size = definedExternally
    open fun add(size: Array<Number>): Size = definedExternally
    open fun subtract(size: Size): Size = definedExternally
    open fun subtract(size: Array<Number>): Size = definedExternally
    open fun multiply(size: Size): Size = definedExternally
    open fun multiply(size: Array<Number>): Size = definedExternally
    open fun multiply(size: Number): Size = definedExternally
    open fun divide(size: Size): Size = definedExternally
    open fun divide(size: Array<Number>): Size = definedExternally
    open fun divide(size: Number): Size = definedExternally
    companion object {
        fun min(size1: Size, size2: Size): Size = definedExternally
        fun max(size1: Size, size2: Size): Size = definedExternally
        fun random(): Size = definedExternally
    }
}
external interface IFrameEvent {
    var count: Number
    var time: Number
    var delta: Number
}
external interface `T$0` {
    var applyMatrix: Boolean
    var handleSize: Number
    var hitTolerance: Number
}
external open class PaperScope {
    open var version: String = definedExternally
    open var settings: `T$0` = definedExternally
    open var project: Project = definedExternally
    open var projects: Array<Project> = definedExternally
    open var view: View = definedExternally
    open var tool: Tool = definedExternally
    open var tools: Array<Tool> = definedExternally
    open fun install(scope: Any): Unit = definedExternally
    open fun setup(canvas: String): Unit = definedExternally
    open fun setup(canvas: HTMLCanvasElement): Unit = definedExternally
    open fun activate(): Unit = definedExternally
    companion object {
        fun get(id: String): PaperScope = definedExternally
    }
}
external interface `T$1` {
    var insert: Boolean? get() = definedExternally; set(value) = definedExternally
    var deep: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface `T$2` {
    var tolerance: Number? get() = definedExternally; set(value) = definedExternally
    var `class`: String? get() = definedExternally; set(value) = definedExternally
    var fill: Boolean? get() = definedExternally; set(value) = definedExternally
    var stroke: Boolean? get() = definedExternally; set(value) = definedExternally
    var segments: Boolean? get() = definedExternally; set(value) = definedExternally
    var curves: Boolean? get() = definedExternally; set(value) = definedExternally
    var handles: Boolean? get() = definedExternally; set(value) = definedExternally
    var ends: Boolean? get() = definedExternally; set(value) = definedExternally
    var bounds: Boolean? get() = definedExternally; set(value) = definedExternally
    var center: Boolean? get() = definedExternally; set(value) = definedExternally
    var guides: Boolean? get() = definedExternally; set(value) = definedExternally
    var selected: Boolean? get() = definedExternally; set(value) = definedExternally
    var match: ((hit: HitResult) -> Boolean)? get() = definedExternally; set(value) = definedExternally
}
external interface `T$3` {
    var asString: Boolean? get() = definedExternally; set(value) = definedExternally
    var precision: Number? get() = definedExternally; set(value) = definedExternally
}
external interface `T$4` {
    var asString: Boolean? get() = definedExternally; set(value) = definedExternally
    var precision: Number? get() = definedExternally; set(value) = definedExternally
    var matchShapes: Boolean? get() = definedExternally; set(value) = definedExternally
}
external interface `T$5` {
    var move: Boolean? get() = definedExternally; set(value) = definedExternally
    var drag: Boolean? get() = definedExternally; set(value) = definedExternally
    var down: Boolean? get() = definedExternally; set(value) = definedExternally
    var up: Boolean? get() = definedExternally; set(value) = definedExternally
}
external open class Item {
    open var tangent: Point = definedExternally
    open var normal: Point = definedExternally
    open var curvature: Number = definedExternally
    open var id: Number = definedExternally
    open var className: String = definedExternally
    open var name: String = definedExternally
    open var style: Style = definedExternally
    open var visible: Boolean = definedExternally
    open var blendMode: String = definedExternally
    open var opacity: Number = definedExternally
    open var selected: Boolean = definedExternally
    open var clipMask: Boolean = definedExternally
    open var data: Any = definedExternally
    open var position: Point = definedExternally
    open var pivot: Point = definedExternally
    open var bounds: Rectangle = definedExternally
    open var strokeBounds: Rectangle = definedExternally
    open var handleBounds: Rectangle = definedExternally
    open var rotation: Number = definedExternally
    open var scaling: Point = definedExternally
    open var matrix: Matrix = definedExternally
    open var globalMatrix: Matrix = definedExternally
    open var applyMatrix: Boolean = definedExternally
    open var project: Project = definedExternally
    open var view: View = definedExternally
    open var layer: Layer = definedExternally
    open var parent: Item = definedExternally
    open var children: Array<Item> = definedExternally
    open var firstChild: Item = definedExternally
    open var lastChild: Item = definedExternally
    open var nextSibling: Item = definedExternally
    open var previousSibling: Item = definedExternally
    open var index: Number = definedExternally
    open var strokeColor: dynamic /* String | Color */ = definedExternally
    open var strokeWidth: Number = definedExternally
    open var strokeCap: String = definedExternally
    open var strokeJoin: String = definedExternally
    open var dashOffset: Number = definedExternally
    open var strokeScaling: Boolean = definedExternally
    open var dashArray: Array<Number> = definedExternally
    open var miterLimit: Number = definedExternally
    open var windingRule: String = definedExternally
    open var fillColor: dynamic /* String | Color */ = definedExternally
    open var fillRule: String = definedExternally
    open var selectedColor: dynamic /* String | Color */ = definedExternally
    open var onFrame: (event: IFrameEvent) -> Unit = definedExternally
    open var onMouseDown: (event: MouseEvent) -> Unit = definedExternally
    open var onMouseDrag: (event: MouseEvent) -> Unit = definedExternally
    open var onMouseUp: (event: MouseEvent) -> Unit = definedExternally
    open var onClick: (event: MouseEvent) -> Unit = definedExternally
    open var onDoubleClick: (event: MouseEvent) -> Unit = definedExternally
    open var onMouseMove: (event: MouseEvent) -> Unit = definedExternally
    open var onMouseEnter: (event: MouseEvent) -> Unit = definedExternally
    open var onMouseLeave: (event: MouseEvent) -> Unit = definedExternally
    open fun set(props: Any): Item = definedExternally
    open fun clone(options: `T$1`? = definedExternally /* null */): Item = definedExternally
    open fun copyTo(item: Item): Item = definedExternally
    open fun rasterize(resolution: Number): Raster = definedExternally
    open fun contains(point: Point): Boolean = definedExternally
    open fun isInside(rect: Rectangle): Boolean = definedExternally
    open fun intersects(item: Item): Boolean = definedExternally
    open fun hitTest(point: Point, options: `T$2`? = definedExternally /* null */): HitResult = definedExternally
    open fun matches(match: Any): Boolean = definedExternally
    open fun matches(name: String, compare: Any): Boolean = definedExternally
    open fun getItems(match: Any): Array<Item> = definedExternally
    open fun getItem(match: Any): Item = definedExternally
    open fun exportJSON(options: `T$3`? = definedExternally /* null */): String = definedExternally
    open fun importJSON(json: String): Unit = definedExternally
    open fun exportSVG(options: `T$4`? = definedExternally /* null */): SVGElement = definedExternally
    open fun importSVG(svg: String, options: Any? = definedExternally /* null */): Item = definedExternally
    open fun importSVG(svg: SVGElement, options: Any? = definedExternally /* null */): Item = definedExternally
    open fun addChild(item: Item): Item = definedExternally
    open fun insertChild(index: Number, item: Item): Item = definedExternally
    open fun addChildren(items: Array<Item>): Array<Item> = definedExternally
    open fun insertChildren(index: Number, items: Array<Item>): Array<Item> = definedExternally
    open fun insertAbove(item: Item): Item = definedExternally
    open fun insertBelow(item: Item): Item = definedExternally
    open fun moveAbove(item: Item): Boolean = definedExternally
    open fun moveBelow(item: Item): Boolean = definedExternally
    open fun sendToBack(): Unit = definedExternally
    open fun bringToFront(): Unit = definedExternally
    open fun reduce(): Item = definedExternally
    open fun remove(): Boolean = definedExternally
    open fun replaceWith(item: Item): Boolean = definedExternally
    open fun removeChildren(): Array<Item> = definedExternally
    open fun removeChildren(from: Number, to: Number? = definedExternally /* null */): Array<Item> = definedExternally
    open fun reverseChildren(): Unit = definedExternally
    open fun isEmpty(): Boolean = definedExternally
    open fun hasFill(): Boolean = definedExternally
    open fun hasStroke(): Boolean = definedExternally
    open fun hasShadow(): Boolean = definedExternally
    open fun hasChildren(): Boolean = definedExternally
    open fun isInserted(): Boolean = definedExternally
    open fun isAbove(item: Item): Boolean = definedExternally
    open fun isBelow(item: Item): Boolean = definedExternally
    open fun isParent(item: Item): Boolean = definedExternally
    open fun isChild(item: Item): Boolean = definedExternally
    open fun isDescendant(item: Item): Boolean = definedExternally
    open fun isAncestor(item: Item): Boolean = definedExternally
    open fun isGroupedWith(item: Item): Boolean = definedExternally
    open fun translate(delta: Point): Point = definedExternally
    open fun rotate(angle: Number, center: Point? = definedExternally /* null */): Unit = definedExternally
    open fun getRotation(): Number = definedExternally
    open fun scale(scale: Number, center: Point? = definedExternally /* null */): Unit = definedExternally
    open fun scale(hor: Number, ver: Number, center: Point? = definedExternally /* null */): Unit = definedExternally
    open fun shear(shear: Number, center: Point? = definedExternally /* null */): Unit = definedExternally
    open fun shear(hor: Number, ver: Number, center: Point? = definedExternally /* null */): Unit = definedExternally
    open fun skew(skew: Point, center: Point? = definedExternally /* null */): Unit = definedExternally
    open fun skew(hor: Number, ver: Number, center: Point? = definedExternally /* null */): Unit = definedExternally
    open fun transform(matrix: Matrix): Unit = definedExternally
    open fun globalToLocal(point: Point): Point = definedExternally
    open fun localToGlobal(point: Point): Point = definedExternally
    open fun parentToLocal(point: Point): Point = definedExternally
    open fun localToParent(point: Point): Point = definedExternally
    open fun fitBounds(rectangle: Rectangle, fill: Boolean? = definedExternally /* null */): Unit = definedExternally
    open fun on(type: String, callback: (event: ToolEvent) -> Unit): Tool = definedExternally
    open fun on(param: Any): Tool = definedExternally
    open fun off(type: String, callback: (event: ToolEvent) -> Unit): Tool = definedExternally
    open fun off(param: Any): Tool = definedExternally
    open fun emit(type: String, event: Any): Boolean = definedExternally
    open fun responds(type: String): Boolean = definedExternally
    open fun on(type: String, callback: () -> Unit): Item = definedExternally
    open fun on(param: Any): Item = definedExternally
    open fun off(type: String, callback: (event: ToolEvent) -> Unit): Item = definedExternally
    open fun off(param: Any): Item = definedExternally
    open fun emit(type: String, event: Any): Boolean = definedExternally
    open fun responds(type: String): Boolean = definedExternally
    open fun removeOn(`object`: `T$5`): Unit = definedExternally
    open fun removeOnMove(): Unit = definedExternally
    open fun removeOnDown(): Unit = definedExternally
    open fun removeOnDrag(): Unit = definedExternally
    open fun removeOnUp(): Unit = definedExternally
}
external open class Group : Item {
    constructor(children: Array<Item>? = definedExternally /* null */)
    constructor(`object`: Any? = definedExternally /* null */)
    open var clipped: Boolean = definedExternally
}
external open class Layer : Group {
    constructor(children: Array<Item>? = definedExternally /* null */)
    constructor(`object`: Any? = definedExternally /* null */)
    open fun activate(): Unit = definedExternally
}
external open class Shape : Item {
    open var type: String = definedExternally
    open var size: Size = definedExternally
    open var radius: dynamic /* Number | Size */ = definedExternally
    companion object {
        fun Circle(center: Point, radius: Number): Shape = definedExternally
        fun Circle(`object`: Any): Shape = definedExternally
        fun Rectangle(rectangle: Rectangle, radius: Number? = definedExternally /* null */): Shape = definedExternally
        fun Rectangle(point: Point, size: Size): Shape = definedExternally
        fun Rectangle(from: Point, to: Point): Shape = definedExternally
        fun Rectangle(`object`: Any): Shape = definedExternally
        fun Ellipse(rectangle: Rectangle): Shape = definedExternally
        fun Ellipse(`object`: Any): Shape = definedExternally
    }
}
external open class Raster : Item {
    constructor(source: String? = definedExternally /* null */, position: Point? = definedExternally /* null */)
    constructor(source: HTMLCanvasElement? = definedExternally /* null */, position: Point? = definedExternally /* null */)
    constructor(source: HTMLImageElement? = definedExternally /* null */, position: Point? = definedExternally /* null */)
    constructor(config: Any)
    open var size: Size = definedExternally
    open var width: Number = definedExternally
    open var height: Number = definedExternally
    open var resolution: Size = definedExternally
    open var image: dynamic /* HTMLCanvasElement | HTMLImageElement */ = definedExternally
    open var canvas: HTMLCanvasElement = definedExternally
    open var context: CanvasRenderingContext2D = definedExternally
    open var source: dynamic /* String | HTMLCanvasElement | HTMLImageElement */ = definedExternally
    open fun getSubCanvas(rect: Rectangle): HTMLCanvasElement = definedExternally
    open fun getSubRaster(rect: Rectangle): Raster = definedExternally
    open fun toDataURL(): String = definedExternally
    open fun drawImage(image: HTMLCanvasElement, point: Point): Unit = definedExternally
    open fun drawImage(image: HTMLImageElement, point: Point): Unit = definedExternally
    open fun getAverageColor(`object`: Point): Color = definedExternally
    open fun getAverageColor(`object`: Rectangle): Color = definedExternally
    open fun getAverageColor(`object`: Path): Color = definedExternally
    open fun getPixel(x: Number, y: Number): Color = definedExternally
    open fun getPixel(point: Point): Color = definedExternally
    open fun setPixel(x: Number, y: Number, color: Color): Unit = definedExternally
    open fun setPixel(point: Point, color: Color): Unit = definedExternally
    open fun createImageData(size: Size): ImageData = definedExternally
    open fun getImageData(rect: Rectangle): ImageData = definedExternally
    open fun setImageData(data: ImageData, point: Point): Unit = definedExternally
}
external open class PlacedSymbol(symbol: Symbol, point: Point? = definedExternally /* null */) : Item {
    open var symbol: Symbol = definedExternally
}
external open class HitResult {
    open var type: String = definedExternally
    open var name: String = definedExternally
    open var item: Item = definedExternally
    open var location: CurveLocation = definedExternally
    open var color: Color = definedExternally
    open var segment: Segment = definedExternally
    open var point: Point = definedExternally
}
external open class PathItem : Item {
    open var pathData: String = definedExternally
    open fun getIntersections(path: PathItem, sorted: Boolean? = definedExternally /* null */): Array<CurveLocation> = definedExternally
    open fun smooth(): Unit = definedExternally
    open fun moveTo(point: Point): Unit = definedExternally
    open fun lineTo(point: Point): Unit = definedExternally
    open fun cubicCurveTo(handle1: Point, handle2: Point, to: Point): Unit = definedExternally
    open fun quadraticCurveTo(handle: Point, to: Point): Unit = definedExternally
    open fun curveTo(through: Point, to: Point, parameter: Number? = definedExternally /* null */): Unit = definedExternally
    open fun arcTo(through: Point, to: Point): Unit = definedExternally
    open fun arcTo(to: Point, clockwise: Boolean? = definedExternally /* null */): Unit = definedExternally
    open fun closePath(join: Boolean): Unit = definedExternally
    open fun moveBy(to: Point): Unit = definedExternally
    open fun lineBy(to: Point): Unit = definedExternally
    open fun curveBy(through: Point, to: Point, parameter: Number? = definedExternally /* null */): Unit = definedExternally
    open fun cubicCurveBy(handle1: Point, handle2: Point, to: Point): Unit = definedExternally
    open fun quadraticCurveBy(handle: Point, to: Point): Unit = definedExternally
    open fun arcBy(through: Point, to: Point): Unit = definedExternally
    open fun arcBy(to: Point, clockwise: Boolean? = definedExternally /* null */): Unit = definedExternally
    open fun unite(path: PathItem): PathItem = definedExternally
    open fun intersect(path: PathItem): PathItem = definedExternally
    open fun subtract(path: PathItem): PathItem = definedExternally
    open fun exclude(path: PathItem): PathItem = definedExternally
    open fun divide(path: PathItem): PathItem = definedExternally
}
external open class Path : PathItem {
    constructor(segments: Array<Segment>? = definedExternally /* null */)
    constructor(segments: Array<Point>? = definedExternally /* null */)
    constructor(`object`: Any? = definedExternally /* null */)
    constructor(pathData: String? = definedExternally /* null */)
    open var segments: Array<Segment> = definedExternally
    open var firstSegment: Segment = definedExternally
    open var lastSegment: Segment = definedExternally
    open var curves: Array<Curve> = definedExternally
    open var firstCurve: Curve = definedExternally
    open var lastCurve: Curve = definedExternally
    open var closed: Boolean = definedExternally
    open var length: Number = definedExternally
    open var area: Number = definedExternally
    open var fullySelected: Boolean = definedExternally
    open var clockwise: Boolean = definedExternally
    open var interiorPoint: Point = definedExternally
    open fun add(segment: Point): Segment = definedExternally
    open fun add(segment: Segment): Segment = definedExternally
    open fun insert(index: Number, segment: Point): Segment = definedExternally
    open fun insert(index: Number, segment: Segment): Segment = definedExternally
    open fun addSegments(segments: Array<Segment>): Array<Segment> = definedExternally
    open fun insertSegments(index: Number, segments: Array<Segment>): Array<Segment> = definedExternally
    open fun removeSegment(index: Number): Segment = definedExternally
    open fun removeSegments(): Array<Segment> = definedExternally
    open fun removeSegments(from: Number, to: Number? = definedExternally /* null */): Array<Segment> = definedExternally
    open fun flatten(maxDistance: Number): Unit = definedExternally
    open fun simplify(tolerance: Number? = definedExternally /* null */): Unit = definedExternally
    open fun split(offset: Number): Path = definedExternally
    open fun split(location: CurveLocation): Path = definedExternally
    open fun split(index: Number, parameter: Number): Path = definedExternally
    open fun reverse(): Unit = definedExternally
    open fun join(path: Path): Path = definedExternally
    open fun getLocationOf(point: Point): CurveLocation = definedExternally
    open fun getOffsetOf(point: Point): Number = definedExternally
    open fun getLocationAt(offset: Number, isParameter: Boolean? = definedExternally /* null */): CurveLocation = definedExternally
    open fun getPointAt(offset: Number, isPatameter: Boolean? = definedExternally /* null */): Point = definedExternally
    open fun getTangentAt(offset: Number, isPatameter: Boolean? = definedExternally /* null */): Point = definedExternally
    open fun getNormalAt(offset: Number, isParameter: Boolean? = definedExternally /* null */): Point = definedExternally
    open fun getCurvatureAt(offset: Number, isParameter: Boolean? = definedExternally /* null */, point: Point? = definedExternally /* null */): Number = definedExternally
    open fun getNearestPoint(point: Point): Point = definedExternally
    open class Line : Path {
        constructor(from: Point, to: Point)
        constructor(`object`: Any)
    }
    open class Circle : Path {
        constructor(center: Point, radius: Number)
        constructor(`object`: Any)
    }
    open class Rectangle : Path {
        constructor(rectangle: Rectangle, radius: Number? = definedExternally /* null */)
        constructor(point: Point, size: Size)
        constructor(from: Point, to: Point)
        constructor(`object`: Any)
    }
    open class Ellipse : Path {
        constructor(rectangle: Rectangle)
        constructor(`object`: Any)
    }
    open class Arc : Path {
        constructor(from: Point, through: Point, to: Point)
        constructor(`object`: Any)
    }
    open class RegularPolygon : Path {
        constructor(center: Point, sides: Number, radius: Number)
        constructor(`object`: Any)
    }
    open class Star : Path {
        constructor(center: Point, points: Number, radius1: Number, radius2: Number)
        constructor(`object`: Any)
    }
}
external open class CompoundPath : PathItem {
    constructor(`object`: Any)
    constructor(pathData: String)
    open var clockwise: Boolean = definedExternally
    open var firstSegment: Segment = definedExternally
    open var lastSegment: Segment = definedExternally
    open var curves: Array<Curve> = definedExternally
    open var firstCurve: Curve = definedExternally
    open var lastCurve: Curve = definedExternally
    open var area: Number = definedExternally
    open fun reverse(): Unit = definedExternally
}
external open class Segment {
    constructor(point: Point? = definedExternally /* null */, handleIn: Point? = definedExternally /* null */, handleOut: Point? = definedExternally /* null */)
    constructor(`object`: Any? = definedExternally /* null */)
    open var point: Point = definedExternally
    open var handleIn: Point = definedExternally
    open var handleOut: Point = definedExternally
    open var linear: Boolean = definedExternally
    open var selected: Boolean = definedExternally
    open var index: Number = definedExternally
    open var path: Path = definedExternally
    open var curve: Curve = definedExternally
    open var location: CurveLocation = definedExternally
    open var next: Segment = definedExternally
    open var previous: Segment = definedExternally
    open fun isColinear(segment: Segment): Boolean = definedExternally
    open fun isArc(): Boolean = definedExternally
    open fun reverse(): Segment = definedExternally
    open fun remove(): Boolean = definedExternally
    override fun toString(): String = definedExternally
    open fun transform(matrix: Matrix): Unit = definedExternally
}
external open class Curve {
    constructor(segment1: Segment, segment2: Segment)
    constructor(point1: Point, handle1: Point, handle2: Point, point2: Point)
    open var point1: Point = definedExternally
    open var point2: Point = definedExternally
    open var handle1: Point = definedExternally
    open var handle2: Point = definedExternally
    open var segment1: Segment = definedExternally
    open var segment2: Segment = definedExternally
    open var path: Path = definedExternally
    open var index: Number = definedExternally
    open var next: Curve = definedExternally
    open var previous: Curve = definedExternally
    open var selected: Boolean = definedExternally
    open var length: Number = definedExternally
    open var bounds: Rectangle = definedExternally
    open var strokeBounds: Rectangle = definedExternally
    open var handleBounds: Rectangle = definedExternally
    open fun isLinear(): Boolean = definedExternally
    open fun divide(offset: Number? = definedExternally /* null */, isParameter: Boolean? = definedExternally /* null */): Curve = definedExternally
    open fun split(offset: Number? = definedExternally /* null */, isParameter: Boolean? = definedExternally /* null */): Path = definedExternally
    open fun reverse(): Curve = definedExternally
    open fun remove(): Boolean = definedExternally
    open fun clone(): Curve = definedExternally
    override fun toString(): String = definedExternally
    open fun getParameterAt(offset: Point, start: Number? = definedExternally /* null */): Number = definedExternally
    open fun getParameterOf(point: Point): Number = definedExternally
    open fun getLocationAt(offset: Number, isParameter: Boolean? = definedExternally /* null */): CurveLocation = definedExternally
    open fun getLocationOf(point: Point): CurveLocation = definedExternally
    open fun getOffsetOf(point: Point): Number = definedExternally
    open fun getPointAt(offset: Number, isParameter: Boolean? = definedExternally /* null */): Point = definedExternally
    open fun getTangentAt(offset: Number, isParameter: Boolean? = definedExternally /* null */): Point = definedExternally
    open fun getNormalAt(offset: Number, isParameter: Boolean? = definedExternally /* null */): Point = definedExternally
    open fun getCurvatureAt(offset: Number, isParameter: Boolean? = definedExternally /* null */): Point = definedExternally
}
external open class CurveLocation(curve: Curve, parameter: Number, point: Point) {
    open var segment: Segment = definedExternally
    open var curve: Curve = definedExternally
    open var intersection: CurveLocation = definedExternally
    open var path: Path = definedExternally
    open var index: Number = definedExternally
    open var offset: Number = definedExternally
    open var curveOffset: Number = definedExternally
    open var parameter: Number = definedExternally
    open var point: Point = definedExternally
    open var distance: Number = definedExternally
    open fun equals(location: CurveLocation): Boolean = definedExternally
    override fun toString(): String = definedExternally
}
external interface `T$6` {
    var tolerance: Number? get() = definedExternally; set(value) = definedExternally
    var `class`: String? get() = definedExternally; set(value) = definedExternally
    var fill: Boolean? get() = definedExternally; set(value) = definedExternally
    var stroke: Boolean? get() = definedExternally; set(value) = definedExternally
    var segments: Boolean? get() = definedExternally; set(value) = definedExternally
    var curves: Boolean? get() = definedExternally; set(value) = definedExternally
    var handles: Boolean? get() = definedExternally; set(value) = definedExternally
    var ends: Boolean? get() = definedExternally; set(value) = definedExternally
    var bounds: Boolean? get() = definedExternally; set(value) = definedExternally
    var center: Boolean? get() = definedExternally; set(value) = definedExternally
    var guides: Boolean? get() = definedExternally; set(value) = definedExternally
    var selected: Boolean? get() = definedExternally; set(value) = definedExternally
}
external open class Project {
    constructor(element: String)
    constructor(element: HTMLCanvasElement)
    open var view: View = definedExternally
    open var currentStyle: Style = definedExternally
    open var index: Number = definedExternally
    open var layers: Array<Layer> = definedExternally
    open var activeLayer: Layer = definedExternally
    open var symbols: Array<Symbol> = definedExternally
    open fun activate(): Unit = definedExternally
    open fun clear(): Unit = definedExternally
    open fun isEmpty(): Boolean = definedExternally
    open fun remove(): Unit = definedExternally
    open fun selectAll(): Unit = definedExternally
    open fun deselectAll(): Unit = definedExternally
    open fun hitTest(point: Point, options: `T$6`? = definedExternally /* null */): HitResult = definedExternally
    open fun getItems(match: Any): Array<Item> = definedExternally
    open fun getItem(match: Any): Item = definedExternally
    open fun exportJSON(options: `T$3`? = definedExternally /* null */): String = definedExternally
    open fun importJSON(json: String): Unit = definedExternally
    open fun exportSVG(options: `T$4`? = definedExternally /* null */): SVGElement = definedExternally
    open fun importSVG(svg: String, options: Any? = definedExternally /* null */): Item = definedExternally
    open fun importSVG(svg: SVGElement, options: Any? = definedExternally /* null */): Item = definedExternally
}
external open class Symbol(item: Item, dontCenter: Boolean? = definedExternally /* null */) {
    open var project: Project = definedExternally
    open var definition: Item = definedExternally
    open fun place(position: Point? = definedExternally /* null */): PlacedSymbol = definedExternally
    open fun clone(): Symbol = definedExternally
}
external open class Style {
    open var view: View = definedExternally
    open var strokeColor: dynamic /* String | Color */ = definedExternally
    open var strokeWidth: Number = definedExternally
    open var strokeCap: String = definedExternally
    open var strokeJoin: String = definedExternally
    open var strokeScaling: Boolean = definedExternally
    open var dashOffset: Number = definedExternally
    open var dashArray: Array<Number> = definedExternally
    open var miterLimit: Number = definedExternally
    open var fillColor: dynamic /* String | Color */ = definedExternally
    open var shadowColor: dynamic /* String | Color */ = definedExternally
    open var shadowBlur: Number = definedExternally
    open var shadowOffset: Point = definedExternally
    open var selectedColor: dynamic /* String | Color */ = definedExternally
    open var fontFamily: String = definedExternally
    open var fontWeight: dynamic /* String | Number */ = definedExternally
    open var fontSize: dynamic /* String | Number */ = definedExternally
    open var leading: dynamic /* String | Number */ = definedExternally
    open var justification: String = definedExternally
}
external interface IHSBColor {
    var hue: Number? get() = definedExternally; set(value) = definedExternally
    var saturation: Number? get() = definedExternally; set(value) = definedExternally
    var brightness: Number? get() = definedExternally; set(value) = definedExternally
    var alpha: Number? get() = definedExternally; set(value) = definedExternally
}
external interface IHSLColor {
    var hue: Number? get() = definedExternally; set(value) = definedExternally
    var saturation: Number? get() = definedExternally; set(value) = definedExternally
    var lightness: Number? get() = definedExternally; set(value) = definedExternally
    var alpha: Number? get() = definedExternally; set(value) = definedExternally
}
external interface IGradientColor {
    var gradient: Gradient? get() = definedExternally; set(value) = definedExternally
    var origin: Point? get() = definedExternally; set(value) = definedExternally
    var destination: Point? get() = definedExternally; set(value) = definedExternally
    var radial: Boolean? get() = definedExternally; set(value) = definedExternally
}
external open class Color {
    constructor(red: Number, green: Number, blue: Number, alpha: Number? = definedExternally /* null */)
    constructor(gray: Number, alpha: Number? = definedExternally /* null */)
    constructor(`object`: IHSBColor)
    constructor(`object`: IHSLColor)
    constructor(`object`: IGradientColor)
    constructor(color: Gradient, origin: Point, destination: Point, highlight: Point? = definedExternally /* null */)
    constructor(hex: String)
    open var type: String = definedExternally
    open var components: Number = definedExternally
    open var alpha: Number = definedExternally
    open var red: Number = definedExternally
    open var green: Number = definedExternally
    open var blue: Number = definedExternally
    open var gray: Number = definedExternally
    open var hue: Number = definedExternally
    open var saturation: Number = definedExternally
    open var brightness: Number = definedExternally
    open var lightness: Number = definedExternally
    open var gradient: Gradient = definedExternally
    open var highlight: Point = definedExternally
    open fun convert(type: String): Color = definedExternally
    open fun hasAlpha(): Boolean = definedExternally
    open fun equals(color: Color): Boolean = definedExternally
    open fun clone(): Color = definedExternally
    override fun toString(): String = definedExternally
    open fun toCSS(hex: Boolean): String = definedExternally
    open fun transform(matrix: Matrix): Unit = definedExternally
}
external open class Gradient {
    open var stops: Array<GradientStop> = definedExternally
    open var radial: Boolean = definedExternally
    open fun clone(): Gradient = definedExternally
    open fun equals(gradient: Gradient): Boolean = definedExternally
}
external open class GradientStop(color: Color? = definedExternally /* null */, rampPoint: Number? = definedExternally /* null */) {
    open var rampPoint: Number = definedExternally
    open var color: Color = definedExternally
    open fun clone(): GradientStop = definedExternally
}
external open class View {
    open var element: HTMLCanvasElement = definedExternally
    open var pixelRatio: Number = definedExternally
    open var resolution: Number = definedExternally
    open var viewSize: Size = definedExternally
    open var bounds: Rectangle = definedExternally
    open var size: Size = definedExternally
    open var center: Point = definedExternally
    open var zoom: Number = definedExternally
    open var onFrame: (event: IFrameEvent) -> Unit = definedExternally
    open var onResize: (event: Event) -> Unit = definedExternally
    open var onMouseDown: (event: MouseEvent) -> Unit = definedExternally
    open var onMouseDrag: (event: MouseEvent) -> Unit = definedExternally
    open var onMouseUp: (event: MouseEvent) -> Unit = definedExternally
    open var onClick: (event: MouseEvent) -> Unit = definedExternally
    open var onDoubleClick: (event: MouseEvent) -> Unit = definedExternally
    open var onMouseMove: (event: MouseEvent) -> Unit = definedExternally
    open var onMouseEnter: (event: MouseEvent) -> Unit = definedExternally
    open var onMouseLeave: (event: MouseEvent) -> Unit = definedExternally
    open fun remove(): Unit = definedExternally
    open fun isVisible(): Boolean = definedExternally
    open fun scrollBy(point: Point): Unit = definedExternally
    open fun play(): Unit = definedExternally
    open fun pause(): Unit = definedExternally
    open fun update(): Unit = definedExternally
    open fun projectToView(point: Point): Point = definedExternally
    open fun viewToProject(point: Point): Point = definedExternally
    open fun on(type: String, callback: (event: Event) -> Unit): Item = definedExternally
    open fun on(param: Any): Item = definedExternally
    open fun off(type: String, callback: (event: Event) -> Unit): Item = definedExternally
    open fun off(param: Any): Item = definedExternally
    open fun emit(type: String, event: Any): Boolean = definedExternally
    open fun responds(type: String): Boolean = definedExternally
    open fun draw(): Unit = definedExternally
}
external open class Tool {
    open var minDistance: Number = definedExternally
    open var maxDistance: Number = definedExternally
    open var fixedDistance: Number = definedExternally
    open var onMouseDown: (event: ToolEvent) -> Unit = definedExternally
    open var onMouseDrag: (event: ToolEvent) -> Unit = definedExternally
    open var onMouseMove: (event: ToolEvent) -> Unit = definedExternally
    open var onMouseUp: (event: ToolEvent) -> Unit = definedExternally
    open var onKeyDown: (event: KeyEvent) -> Unit = definedExternally
    open var onKeyUp: (event: KeyEvent) -> Unit = definedExternally
    open fun activate(): Unit = definedExternally
    open fun remove(): Unit = definedExternally
    open fun on(type: String, callback: (event: ToolEvent) -> Unit): Tool = definedExternally
    open fun on(param: Any): Tool = definedExternally
    open fun off(type: String, callback: (event: ToolEvent) -> Unit): Tool = definedExternally
    open fun off(param: Any): Tool = definedExternally
    open fun emit(type: String, event: Any): Boolean = definedExternally
    open fun responds(type: String): Boolean = definedExternally
}
external open class Event {
    open var modifiers: Any = definedExternally
}
external open class ToolEvent : Event {
    open var type: String = definedExternally
    open var point: Point = definedExternally
    open var lastPoint: Point = definedExternally
    open var downPoint: Point = definedExternally
    open var middlePoint: Point = definedExternally
    open var delta: Point = definedExternally
    open var count: Number = definedExternally
    open var item: Item? = definedExternally
    override fun toString(): String = definedExternally
}
external open class Key {
    companion object {
        fun isDown(key: String): Boolean = definedExternally
    }
}
external open class KeyEvent : Event {
    open var type: String = definedExternally
    open var character: String = definedExternally
    open var key: String = definedExternally
    override fun toString(): String = definedExternally
}
external open class TextItem : Item {
    open var content: String = definedExternally
    open var fontFamily: String = definedExternally
    open var fontWeight: dynamic /* String | Number */ = definedExternally
    open var fontSize: dynamic /* String | Number */ = definedExternally
    open var leading: dynamic /* String | Number */ = definedExternally
    open var justification: String = definedExternally
}
external open class PointText : TextItem {
    constructor(point: Point)
    constructor(`object`: Any)
    open var point: Point = definedExternally
}
external open class MouseEvent(type: String, event: MouseEvent, point: Point, target: Item, delta: Point) : Event {
    open var event: MouseEvent = definedExternally
    open var point: Point = definedExternally
    open var lastPoint: Point = definedExternally
    open var delta: Point = definedExternally
    open var target: Item = definedExternally
    open var currentTarget: Item = definedExternally
    open var type: dynamic /* String /* "mousedown" */ | String /* "mouseup" */ | String /* "mousedrag" */ | String /* "click" */ | String /* "doubleclick" */ | String /* "mousemove" */ | String /* "mouseenter" */ | String /* "mouseleave" */ */ = definedExternally
    open fun timeStamp(): Number = definedExternally
    open fun preventDefault(): Unit = definedExternally
    open fun stopPropagation(): Unit = definedExternally
    open fun stop(): Unit = definedExternally
}
