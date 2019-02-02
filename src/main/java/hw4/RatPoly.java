package hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <b>RatPoly</b> represents an immutable single-variate polynomial expression.
 * RatPolys are sums of RatTerms with non-negative exponents.
 * <p>
 *
 * Examples of RatPolys include "0", "x-10", and "x^3-2*x^2+5/3*x+3", and "NaN".
 */
// See RatNum's documentation for a definition of "immutable".
public final class RatPoly {

  /** Holds all the RatTerms in this RatPoly */
  private final List<RatTerm> terms;

  // Definitions:
  // For a RatPoly p, let C(p,i) be "p.terms.get(i).getCoeff()" and
  // E(p,i) be "p.terms.get(i).getExpt()"
  // length(p) be "p.terms.size()"
  // (These are helper functions that will make it easier for us
  // to write the remainder of the specifications. They are not
  // executable code; they just represent complex expressions in a
  // concise manner, so that we can stress the important parts of
  // other expressions in the spec rather than get bogged down in
  // the details of how we extract the coefficient for the 2nd term
  // or the exponent for the 5th term. So when you see C(p,i),
  // think "coefficient for the ith term in p".)
  //
  // Abstraction Function:
  // RatPoly, p, represents the polynomial equal to the sum of the
  // RatTerms contained in 'terms':
  // sum (0 <= i < length(p)): p.terms.get(i)
  // If there are no terms, then the RatPoly represents the zero
  // polynomial.
  //
  // Representation Invariant for every RatPoly p:
  // terms != null &&
  // forall i such that (0 <= i < length(p)), C(p,i) != 0 &&
  // forall i such that (0 <= i < length(p)), E(p,i) >= 0 &&
  // forall i such that (0 <= i < length(p) - 1), E(p,i) > E(p, i+1)
  // In other words:
  // * The terms field always points to some usable object.
  // * No term in a RatPoly has a zero coefficient.
  // * No term in a RatPoly has a negative exponent.
  // * The terms in a RatPoly are sorted in descending exponent order.
  // (It is implied that 'terms' does not contain any null elements by the
  // above
  // invariant.)

  /** A constant holding a Not-a-Number (NaN) value of type RatPoly */
  public static final RatPoly NaN = new RatPoly(RatTerm.NaN);

  /** A constant holding a zero value of type RatPoly */
  public static final RatPoly ZERO = new RatPoly();

  /**
   * @spec.effects Constructs a new Poly, "0".
   */
  public RatPoly() {
    terms = new ArrayList<RatTerm>();
    checkRep();
  }

  /**
   * @param rt The single term which the new RatPoly equals.
   * @spec.requires rt.getExpt() &gt;= 0
   * @spec.effects Constructs a new Poly equal to "rt". If rt.getCoeff() is zero,
   *          constructs a "0" polynomial.
   */
  public RatPoly(RatTerm rt) {
    this();
    if (!rt.isZero()) {
      terms.add(rt);
    }
    checkRep();
  }

  /**
   * @param c The constant in the term which the new RatPoly equals.
   * @param e The exponent in the term which the new RatPoly equals.
   * @spec.requires e &gt;= 0
   * @spec.effects Constructs a new Poly equal to "c*x^e". If c is zero, constructs
   *          a "0" polynomial.
   */
  public RatPoly (int c, int e) {
    this(new RatTerm(new RatNum(c),e));
  }

  /**
   * @param rt A list of terms to be contained in the new RatPoly.
   * @spec.requires 'rt' satisfies clauses given in rep. invariant
   * @spec.effects Constructs a new Poly using 'rt' as part of the representation.
   *          The method does not make a copy of 'rt'.
   */
  private RatPoly (List<RatTerm> rt) {
    terms = rt;
    // The spec tells us that we don't need to make a copy of 'rt'
    checkRep();
  }

  /**
   * Returns the degree of this RatPoly.
   *
   * @spec.requires !this.isNaN()
   * @return the largest exponent with a non-zero coefficient, or 0 if this is
   *         "0".
   */
  public int degree() {
    if(terms.isEmpty()) {
      return 0;
    }
    return terms.get(0).getExpt();
  }

  /**
   * Gets the RatTerm associated with degree 'deg'
   *
   * @param deg The degree for which to find the corresponding RatTerm.
   * @spec.requires !this.isNaN()
   * @return the RatTerm of degree 'deg'. If there is no term of degree 'deg'
   *         in this poly, then returns the zero RatTerm.
   */
  public RatTerm getTerm (int deg) {
    for (RatTerm term : terms) {
      if (term.getExpt() == deg) {
        return term;
      }
    }
    return RatTerm.ZERO;
  }

  /**
   * Returns true if this RatPoly is not-a-number.
   *
   * @return true if and only if this has some coefficient = "NaN".
   */
  public boolean isNaN() {
    for (RatTerm term : terms) {
      if (term.isNaN()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Helper procedure: Inserts a term into a sorted sequence of terms,
   * preserving the sorted nature of the sequence. If a term with the given
   * degree already exists, adds their coefficients.
   *
   * Definitions: Let a "Sorted List<RatTerm>" be a List<RatTerm> V such
   * that [1] V is sorted in descending exponent order && [2] there are no two
   * RatTerms with the same exponent in V && [3] there is no RatTerm in V with
   * a coefficient equal to zero
   *
   * For a Sorted List<RatTerm> V and integer e, let cofind(V, e) be either
   * the coefficient for a RatTerm rt in V whose exponent is e, or zero if
   * there does not exist any such RatTerm in V. (This is like the coeff
   * function of RatPoly.) We will write sorted(lst) to denote that lst is a
   * Sorted List<RatTerm>, as defined above.
   *
   * @param lst The list into which newTerm should be inserted.
   * @param newTerm The term to be inserted into the list.
   * @spec.requires lst != null && sorted(lst)
   * @spec.modifies lst
   * @spec.effects sorted(lst_post) && (cofind(lst_post,newTerm.getExpt()) =
   *          cofind(lst,newTerm.getExpt()) + newTerm.getCoeff())
   */
  private static void sortedInsert(List<RatTerm> lst, RatTerm newTerm) {
    //add to end, bubble to correct spot, add to current if expts are the same
    if (!newTerm.isZero()) {
      lst.add(newTerm);
    }
    for (int i = lst.size() - 1; i > 0; i--) {
      //bubbles newTerm to correct position
      if (lst.get(i).getExpt() > lst.get(i - 1).getExpt()) {
        RatTerm temp = lst.get(i);
        lst.set(i, lst.get(i - 1));
        lst.set(i - 1, temp);
      }
    }
    combineSameExpts(lst);
  }

  /**
   * Helper procedure for sortedInsert: after sorted insert has bubbled the new value to it's spot defined
   * by it's degree, checks for other values of same degree in the list and adds them if found. If
   * combination is 0, removes both values.
   *
   * @param lst The list into which newTerm should be inserted.
   * @spec.modifies lst
   */
  private static void combineSameExpts(List<RatTerm> lst) {
    for(int i = 0; i < lst.size() - 1; i++) {
      if(lst.get(i).getExpt() == lst.get(i + 1).getExpt()) {
        RatTerm addedTerm = lst.get(i).add(lst.get(i + 1));
        if(addedTerm.isZero()) {
          //if added values = 0, removes both values
          lst.remove(i);
          lst.remove(i + 1);
        } else {
          //if added values != 0, only removes one of the values
          //and adds it to the other
          lst.set(i, addedTerm);
          lst.remove(i + 1);
        }
      }
    }
  }

  /**
   * Return the additive inverse of this RatPoly.
   *
   * @return a RatPoly equal to "0 - this"; if this.isNaN(), returns some r
   *         such that r.isNaN()
   */
  public RatPoly negate() {
    List<RatTerm> result = new ArrayList<>();
    for (int i = 0; i < terms.size(); i++) {
      result.add(terms.get(i).negate());
    }
    return new RatPoly(result);
  }

  /**
   * Addition operation.
   *
   * @param p The other value to be added.
   * @spec.requires p != null
   * @return a RatPoly, r, such that r = "this + p"; if this.isNaN() or
   *         p.isNaN(), returns some r such that r.isNaN()
   */
  public RatPoly add(RatPoly p) {
    List<RatTerm> addedTerms = new ArrayList<>();
    int biggerListDegree = Math.max(this.degree(), p.degree());
    for(int i = biggerListDegree; i >= 0; i--) {
      //Iterates through every value in the bigger of the two RatPolys,
      //combining the two values with the same exponents.
      RatTerm thisTerm = this.getTerm(i);
      RatTerm pTerm = p.getTerm(i);
      RatTerm comboTerm = thisTerm.add(pTerm);
      if(!comboTerm.equals(RatTerm.ZERO)) {
        addedTerms.add(comboTerm);
      }
    }
    return new RatPoly(addedTerms);
  }

  /**
   * Subtraction operation.
   *
   * @param p The value to be subtracted.
   * @spec.requires p != null
   * @return a RatPoly, r, such that r = "this - p"; if this.isNaN() or
   *         p.isNaN(), returns some r such that r.isNaN()
   */
  public RatPoly sub(RatPoly p) {
    return this.add(p.negate());
  }

  /**
   * Multiplication operation.
   *
   * @param p The other value to be multiplied.
   * @spec.requires p != null
   * @return a RatPoly, r, such that r = "this * p"; if this.isNaN() or
   *         p.isNaN(), returns some r such that r.isNaN()
   */
  public RatPoly mul(RatPoly p) {
    if(this.isNaN() || p.isNaN()) {
      return NaN;
    }
    RatPoly result = new RatPoly();
    for(RatTerm term : terms) {
      for(int j = 0; j <= p.degree(); j++) {
        result = result.add(new RatPoly(term.mul(p.getTerm(j))));
      }
    }
    return result;
  }

  /**
   * Division operation (truncating).
   *
   * @param p The divisor.
   * @spec.requires p != null
   * @return a RatPoly, q, such that q = "this / p"; if p = 0 or this.isNaN()
   *         or p.isNaN(), returns some q such that q.isNaN()
   *         <p>
   *
   * Division of polynomials is defined as follows: Given two polynomials u
   * and v, with v != "0", we can divide u by v to obtain a quotient
   * polynomial q and a remainder polynomial r satisfying the condition u = "q *
   * v + r", where the degree of r is strictly less than the degree of v, the
   * degree of q is no greater than the degree of u, and r and q have no
   * negative exponents.
   * <p>
   *
   * For the purposes of this class, the operation "u / v" returns q as
   * defined above.
   * <p>
   *
   * The following are examples of div's behavior: "x^3-2*x+3" / "3*x^2" =
   * "1/3*x" (with r = "-2*x+3"). "x^2+2*x+15 / 2*x^3" = "0" (with r =
   * "x^2+2*x+15"). "x^3+x-1 / x+1 = x^2-x+2 (with r = "-3").
   * <p>
   *
   * Note that this truncating behavior is similar to the behavior of integer
   * division on computers.
   */
  public RatPoly div(RatPoly p) {
    if (p.isNaN() || p.equals(ZERO) || this.isNaN()) {
      return NaN;
    }
    if (p.degree() == 0) {
      //if p is a constant, divide all values by coeff and return
      List<RatTerm> divByConstant = new ArrayList<>();
      for (RatTerm term : terms) {
        divByConstant.add(new RatTerm(term.getCoeff().div(p.getTerm(0).getCoeff()),
                term.getExpt()));
      }
      return new RatPoly(divByConstant);
    } else {
      //if p is not a constant, follow polynomial division procedure
      List<RatTerm> quotient = new ArrayList<>();
      RatPoly remainder = this;
      //copy all terms to remainder, divide out one by one
      //{{Inv: quotient is the terms of p divisible by this.terms_0 ... this.terms_i-1
      //       divided by this.terms_0 ... this.terms_i-1
      //       and r is the terms of p not divisible by this.terms_0 ... this.terms_i-1
      //       and the degree of remainder is not 0}}
      while (remainder.degree() >= p.degree() && remainder.degree() != 0) {
        RatTerm newTerm = remainder.getTerm(remainder.degree()).div(p.getTerm(p.degree()));
        quotient.add(newTerm);
        remainder = remainder.sub(p.mul(new RatPoly(newTerm)));
      }
      return new RatPoly(quotient);
    }
  }

  /**
   * Return the derivative of this RatPoly.
   *
   * @return a RatPoly, q, such that q = dy/dx, where this == y. In other
   *         words, q is the derivative of this. If this.isNaN(), then return
   *         some q such that q.isNaN()
   *
   * <p>
   * The derivative of a polynomial is the sum of the derivative of each term.
   */
  public RatPoly differentiate() {
    List<RatTerm> derivatives = new ArrayList<>();
    for(RatTerm term : terms) {
      if (!term.differentiate().isZero()) {
        derivatives.add(term.differentiate());
      }
    }
    return new RatPoly(derivatives);
  }

  /**
   * Returns the antiderivative of this RatPoly.
   *
   * @param integrationConstant The constant of integration to use when
   *  computating the antiderivative.
   * @spec.requires integrationConstant != null
   * @return a RatPoly, q, such that dq/dx = this and the constant of
   *         integration is "integrationConstant" In other words, q is the
   *         antiderivative of this. If this.isNaN() or
   *         integrationConstant.isNaN(), then return some q such that
   *         q.isNaN()
   *
   * <p>
   * The antiderivative of a polynomial is the sum of the antiderivative of
   * each term plus some constant.
   */
  public RatPoly antiDifferentiate(RatNum integrationConstant) {
    if(integrationConstant.isNaN() || this.isNaN()) {
      return NaN;
    }
    List<RatTerm> antiderivatives = new ArrayList<>();
    for(RatTerm term : terms) {
      antiderivatives.add(term.antiDifferentiate());
    }
    if(!integrationConstant.equals(RatNum.ZERO)) {
      //if constant is nonzero add to terms
      antiderivatives.add(new RatTerm(integrationConstant, 0));
    }
    return new RatPoly(antiderivatives);
  }

  /**
   * Returns the integral of this RatPoly, integrated from lowerBound to
   * upperBound.
   *
   * <p>
   * The Fundamental Theorem of Calculus states that the definite integral of
   * f(x) with bounds a to b is F(b) - F(a) where dF/dx = f(x) NOTE: Remember
   * that the lowerBound can be higher than the upperBound.
   *
   * @param lowerBound The lower bound of integration.
   * @param upperBound The upper bound of integration.
   * @return a double that is the definite integral of this with bounds of
   *         integration between lowerBound and upperBound. If this.isNaN(),
   *         or either lowerBound or upperBound is Double.NaN, return
   *         Double.NaN.
   */
  public double integrate(double lowerBound, double upperBound) {
    RatPoly antiDer = this.antiDifferentiate(RatNum.ZERO);
    return antiDer.eval(upperBound)- antiDer.eval(lowerBound);
  }

  /**
   * Returns the value of this RatPoly, evaluated at d.
   *
   * @param d The value at which to evaluate this polynomial.
   * @return the value of this polynomial when evaluated at 'd'. For example,
   *         "x+2" evaluated at 3 is 5, and "x^2-x" evaluated at 3 is 6. if
   *         (this.isNaN() == true), return Double.NaN
   */
  public double eval(double d) {
    double result = 0;
    for (RatTerm term: terms) {
      result += term.eval(d);
    }
    return result;
  }

  /**
   * Returns a string representation of this RatPoly.
   *
   * @return A String representation of the expression represented by this,
   *         with the terms sorted in order of degree from highest to lowest.
   *         <p>
   *         There is no whitespace in the returned string.
   *         <p>
   *         If the polynomial is itself zero, the returned string will just
   *         be "0".
   *         <p>
   *         If this.isNaN(), then the returned string will be just "NaN"
   *         <p>
   *         The string for a non-zero, non-NaN poly is in the form
   *         "(-)T(+|-)T(+|-)...", where "(-)" refers to a possible minus
   *         sign, if needed, and "(+|-)" refer to either a plus or minus
   *         sign, as needed. For each term, T takes the form "C*x^E" or "C*x"
   *         where C &gt; 0, UNLESS: (1) the exponent E is zero, in which case T
   *         takes the form "C", or (2) the coefficient C is one, in which
   *         case T takes the form "x^E" or "x". In cases were both (1) and
   *         (2) apply, (1) is used.
   *         <p>
   *         Valid example outputs include "x^17-3/2*x^2+1", "-x+1", "-1/2",
   *         and "0".
   */
  @Override
  public String toString() {
    if (terms.size() == 0) {
      return "0";
    }
    if (isNaN()) {
      return "NaN";
    }
    StringBuilder output = new StringBuilder();
    boolean isFirst = true;
    for (RatTerm rt : terms) {
      if (isFirst) {
        isFirst = false;
        output.append(rt.toString());
      } else {
        if (rt.getCoeff().isNegative()) {
          output.append(rt.toString());
        } else {
          output.append("+" + rt.toString());
        }
      }
    }
    return output.toString();
  }

  /**
   * Builds a new RatPoly, given a descriptive String.
   *
   * @param polyStr A string of the format described in the @spec.requires clause.
   * @spec.requires 'polyStr' is an instance of a string with no spaces that
   *           expresses a poly in the form defined in the toString() method, except that
   *           the ordering of the terms by the degrees is not necessary.
   *           Valid inputs include "0", "x-10", and "x^3-2*x^2+5/3*x+3", and "NaN".
   *
   * @return a RatPoly p such that p.toString() = polyStr
   */
  public static RatPoly valueOf(String polyStr) {

    List<RatTerm> parsedTerms = new ArrayList<RatTerm>();

    // First we decompose the polyStr into its component terms;
    // third arg orders "+" and "-" to be returned as tokens.
    StringTokenizer termStrings = new StringTokenizer(polyStr, "+-", true);

    boolean nextTermIsNegative = false;
    while (termStrings.hasMoreTokens()) {
      String termToken = termStrings.nextToken();

      if (termToken.equals("-")) {
        nextTermIsNegative = true;
      } else if (termToken.equals("+")) {
        nextTermIsNegative = false;
      } else {
        // Not "+" or "-"; must be a term
        RatTerm term = RatTerm.valueOf(termToken);

        // at this point, coeff and expt are initialized.
        // Need to fix coeff if it was preceeded by a '-'
        if (nextTermIsNegative) {
          term = term.negate();
        }

        // accumulate terms of polynomial in 'parsedTerms'
        sortedInsert(parsedTerms, term);
      }
    }
    return new RatPoly(parsedTerms);
  }

  /**
   * Standard hashCode function.
   *
   * @return an int that all objects equal to this will also return.
   */
  @Override
  public int hashCode() {
    // all instances that are NaN must return the same hashcode;
    if (this.isNaN()) {
      return 0;
    }
    return terms.hashCode();
  }

  /**
   * Standard equality operation.
   *
   * @param obj The object to be compared for equality.
   * @return true if and only if 'obj' is an instance of a RatPoly and 'this'
   *         and 'obj' represent the same rational polynomial. Note that all
   *         NaN RatPolys are equal.
   */
  @Override
  public boolean equals(/*@Nullable*/ Object obj) {
    if (obj instanceof RatPoly) {
      RatPoly rp = (RatPoly) obj;

      // special case: check if both are NaN
      if (this.isNaN() && rp.isNaN()) {
        return true;
      } else {
        return terms.equals(rp.terms);
      }
    } else {
      return false;
    }
  }

  /**
   * Checks that the representation invariant holds (if any).
   */
  private void checkRep() {
    assert (terms != null);

    for (int i = 0; i < terms.size(); i++) {
        assert (!terms.get(i).getCoeff().equals(new RatNum(0))) : "zero coefficient";
        assert (terms.get(i).getExpt() >= 0) : "negative exponent";

        if (i < terms.size() - 1)
            assert (terms.get(i + 1).getExpt() < terms.get(i).getExpt()) : "terms out of order";
    }
  }
}
