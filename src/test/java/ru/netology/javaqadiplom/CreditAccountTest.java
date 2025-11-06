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
    /** Тестирование пополнения кошелька. Добавляем число если на счете уже есть деньги
     * initialBalance - начальный баланс для счёта
     * creditLimit - максимальная сумма которую можно задолжать банку
     * rate - ставка кредитования для расчёта долга за отрицательный баланс
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
}
