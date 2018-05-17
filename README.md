# Advanced Types in Scala

## Polymorphism
Polymorphism is the provision of a single interface to entities of different types.

* Subtyping 
    * Method overriding
* Parametric
    * "Generics"
    * We don't know anything about the type, so we can't use any of its properties
* Ad-hoc
    * Each type can have its own implementation
    * __Type classes__
* Bounded parametric
    * We might require some properties for our types but otherwise they can be parametric
    * __Type bounds__ = Subtyping + Parametric ```[A <: Comparable[A]]```
    * __Context bounds__ = Ad-hoc + Parametric ```[A : Numeric]```

## Topics
* Variance
* Type Bounds
* Higher Kinded Types
* Context Bounds
* Bonus: Type Constraints

## Parametric polymorphism in Scala
```scala
class Stack[T] {
    var elems: List[T] = Nil
    def push(x: T) { elems = x :: elems }  
    def top: T = elems.head  
    def pop() {  elems  =  elems.tail  }  
}
```

## Type Bounds
| Name | Syntax | Semantics |
| --- | --- | --- |
| upper bound |```A <: B```  | ```A``` is a subtype of ```B```
| lower bound |```A >: B```  | ```A``` is a supertype of ```B```


```scala
def f[T <: Comparable[T]](t: T) = ???
def f[T >: Comparable[T]](t: T) = ???
```
In Java ```<:``` corresponds to ```extends```.

```java
public <T extends Comparable<T>> void f(T t) {}
```

## Variance
```
If A <: B
CovariantBox[A]     <: CovariantBox[B]
ContravariantBox[B] <: ContravariantBox[A]
```

* Subtyping relationship of a "container" class varying with the subtyping relationship of a "contained" class.
* "Contravariance" "Consumes"
* _Variance is only applicable to classes and traits not to functions!_

| Covariance | Contravariance | Invariance |
| --- | --- | --- |
| ```[+A]``` | ```[-A]```  | ```[A]``` |
| out | in  | in & out |
| varies in the __same__ direction than the inner type | varies in the __opposite__ direction | variance is not related to the inner type |

List is covariant in Scala, but Array is invariant
```
Int <: AnyVal
List[Int] <: List[AnyVal]
Array[Int] <: Array[AnyVal] // This is not true
```

* Use-site vs Declaration-site

##Variance vs Bounds
__Bounds:__  Useful when you want to be generic but require a certain set of methods

__Variance:__  Useful when you want to make a collections that behave the same way as the contained classes

## Higher kinded types

|  | Proper | First-Order | Higher-Order 
| --- | --- | --- | --- |
| Values | 10 | (x: Int) => x | (f: (Int => Int)) => f(10)
| Types  | String | List[T] | Functor[F[_]]

Think of type constructors as lambdas

```
List[T]       ==>  T -> List[T]
Functor[F[_]] ==>  (T -> F[T]) -> Functor[F[T]]
```

Eg: Functor[Try], Try can still hold any value

### Examples
* Monad
* sttp library



## Context Bounds


#### Good sources
* [Type related features in Scala](http://ktoso.github.io/scala-types-of-types/)
* [Variance1](http://blog.kamkor.me/Covariance-And-Contravariance-In-Scala/)
* [Variance2](https://medium.com/@sinisalouc/variance-in-java-and-scala-63af925d21dc)
* [Type Constraints](http://blog.bruchez.name/2015/11/generalized-type-constraints-in-scala.html)
* [Value Classes](https://docs.scala-lang.org/overviews/core/value-classes.html)
