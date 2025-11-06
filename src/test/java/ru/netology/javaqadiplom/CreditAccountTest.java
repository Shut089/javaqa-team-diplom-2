package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    /** Тестирование пополнения кошелька (Позитивное)
     * initialBalance - начальный баланс для счёта
     * creditLimit - максимальная сумма которую можно задолжать банку
     * rate - ставка кредитования для расчёта долга за отрицательный баланс
     */

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }
    /** Тестирование пополнения кошелька (Негативное). Добавляем отрицательное число должно быть 0
        * initialBalance - начальный баланс для счёта
        * creditLimit - максимальная сумма которую можно задолжать банку
        * rate - ставка кредитования для расчёта долга за отрицательный баланс
    */
    @Test
    public void shouldAddToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(-3_000);

        Assertions.assertEquals(0, account.getBalance());
    }
    /** Тестирование пополнения кошелька.
     * Добавляем число если на счете уже есть деньги
          */
    @Test
    public void shouldAddToPositiveBalanceMoreThan0(){
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );
        account.add(2_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    /** Тестирование пополнения кошелька.
     * Добавляем дробное число в пустой кошелек
         */
    @Test
    public void shouldAddToPositiveBalanceFractionalValuesNull(){
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.add(2_555);

        Assertions.assertEquals(2_555, account.getBalance());
    }

    /** Тестирование пополнения кошелька.
     * Добавляем дробное число в не пустой кошелек
     */
    @Test
    public void shouldAddToPositiveBalanceFractionalValuesNotNull(){
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.add(2_555);

        Assertions.assertEquals(3_555, account.getBalance());
    }

    /** Тестирование пополнения кошелька.
     * Добавляем нулевое значение в кошелек
     */
    @Test
    public void shouldAddToPositiveBalance0(){
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.add(0);

        Assertions.assertEquals(0, account.getBalance());
    }

    /** Тестирование пополнения кошелька.
     * Добавляем сразу несколько значений в кошелек
     */
    @Test
    public void shouldAddToPositiveBalanceMany(){
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.add(1_000);
        account.add(2_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    /** Тестирование пополнения кошелька.
     * Проверка на сохранение старого баланса при неуспешной операции.
     * Добавляем отрицательное значение
     */
    @Test
    public void shouldAddToPositiveBalanceMinusSum(){
        CreditAccount account = new CreditAccount(
                100,
                5_000,
                15
        );
        account.add(-50);

        Assertions.assertEquals(100, account.getBalance());
    }
}
