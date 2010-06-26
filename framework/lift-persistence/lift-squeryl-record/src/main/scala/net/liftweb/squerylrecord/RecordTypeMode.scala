/*
 * Copyright 2010 WorldWide Conferencing, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.liftweb {
package squerylrecord {

import _root_.net.liftweb.record.{OptionalTypedField, TypedField}
import _root_.org.squeryl.PrimitiveTypeMode
import _root_.org.squeryl.dsl.ast.SelectElementReference
import _root_.org.squeryl.internals.FieldReferenceLinker

object RecordTypeMode extends RecordTypeMode

/**
 * The implicit conversions in RecordTypeModeBase have higher priority
 * that the ones in RecordTypeMode. That is the reason for having the
 * two splited, otherwise expressions the DSL gets many ambiguous
 * references to implicit conversions.
 * Conversions for Optional record field types are defined in this class and for optionals,
 * they are defined in RecordTypeModeBase. 
 */
trait RecordTypeMode extends RecordTypeModeBase {

  implicit def optionString2ScalarString(i: OptionalTypedField[String]) =
    new SelectElementReference[Option[StringType]](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperStringTypeOption) with StringExpression[Option[StringType]]

  implicit def optionInt2ScalarInt(i: OptionalTypedField[Int]) =
    new SelectElementReference[Option[IntType]](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperIntTypeOption) with NumericalExpression[Option[IntType]]

  implicit def optionLong2ScalarLong(i: OptionalTypedField[LongType]) =
    new SelectElementReference[Option[LongType]](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperLongTypeOption) with NumericalExpression[Option[LongType]]

  implicit def optionDouble2ScalarDouble(i: OptionalTypedField[DoubleType]) =
    new SelectElementReference[Option[DoubleType]](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperDoubleTypeOption) with NumericalExpression[Option[DoubleType]]
  
//  implicit def optionFloat2ScalarFloat(i: OptionalTypedField[FloatType]) =
//    new SelectElementReference[Option[FloatType]](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperFloatTypeOption) with NumericalExpression[Option[FloatType]]

  implicit def optionBoolean2ScalarBoolean(i: OptionalTypedField[BooleanType]) =
    new SelectElementReference[Option[BooleanType]](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperBooleanTypeOption) with BooleanExpression[Option[BooleanType]]

//  implicit def optionDate2ScalarDate(i: OptionalTypedField[DateType]) =
//    new SelectElementReference[Option[DateType]](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperDateTypeOption) with DateExpression[Option[DateType]]

  implicit def optionDecimal2ScalarBoolean(i: OptionalTypedField[BigDecimal]) =
    new SelectElementReference[Option[BigDecimal]](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperBigDecimalTypeOption) with NumericalExpression[Option[BigDecimal]]

}

/**
 * Conversions for all non optional Record types are defined here
 */
trait RecordTypeModeBase extends PrimitiveTypeMode {


  implicit def long2ScalarLong(l: TypedField[Long]) =
    new SelectElementReference[LongType](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperLongType) with NumericalExpression[LongType]

  implicit def int2ScalarInt(i: TypedField[Int]) = 
    new SelectElementReference[IntType](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperIntType) with NumericalExpression[IntType]

  implicit def double2ScalarDouble(d: TypedField[Double]) =
    new SelectElementReference[DoubleType](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperDoubleType) with NumericalExpression[DoubleType]

//  implicit def float2ScalarFloat(d: FloatType) =
//    new SelectElementReference[FloatType](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperFloatType) with NumericalExpression[FloatType]

  implicit def string2ScalarString(s: TypedField[String]) =
    new SelectElementReference[StringType](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperStringType) with StringExpression[StringType]

  implicit def bool2ScalarBoolean(b: TypedField[Boolean]) =
    new SelectElementReference[BooleanType](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperBooleanType) with BooleanExpression[BooleanType]

//  implicit def date2ScalarDate(b: DateType) =
//    new SelectElementReference[DateType](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperDateType) with DateExpression[DateType]

  implicit def decimal2ScalarBoolean(i: TypedField[BigDecimal]) =
    new SelectElementReference[BigDecimal](FieldReferenceLinker.takeLastAccessedFieldReference.get)(createOutMapperBigDecimalType) with NumericalExpression[BigDecimal]
}

}
}
