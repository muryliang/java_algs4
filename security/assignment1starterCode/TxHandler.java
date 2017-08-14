import java.util.ArrayList;

public class TxHandler {

    private UTXOPool up;
    /**
     * Creates a public ledger whose current UTXOPool (collection of unspent transaction outputs) is
     * {@code utxoPool}. This should make a copy of utxoPool by using the UTXOPool(UTXOPool uPool)
     * constructor.
     */
    public TxHandler(UTXOPool utxoPool) {
        // IMPLEMENT THIS
        up = new UTXOPool(utxoPool);
    }

    /**
     * @return true if:
     * (1) all outputs claimed by {@code tx} are in the current UTXO pool, 
     * (2) the signatures on each input of {@code tx} are valid, 
     * (3) no UTXO is claimed multiple times by {@code tx},
     * (4) all of {@code tx}s output values are non-negative, and
     * (5) the sum of {@code tx}s input values is greater than or equal to the sum of its output
     *     values; and false otherwise.
     */
    public boolean isValidTx(Transaction tx) {
        // IMPLEMENT THIS
        // 1
        ArrayList<Transaction.Input> ips = tx.getInputs();
        for (Transaction.Input ip: ips) {
            if (!up.contains(new UTXO(ip.prevTxHash, ip.outputIndex)))
                return false;
        }
        // 2
        Transaction.Output op;
        for (Transaction.Input ip: ips) {
            op = up.getTxOutput(new UTXO(ip.prevTxHash, ip.outputIndex));
            if (!Crypto.verifySignature(op.address, tx.getRawDataToSign(ip.outputIndex), ip.prevTxHash))
                return false;
        }
        // 3
        UTXOPool pool = new UTXOPool();
        UTXO tmputxo;
        for (Transaction.Input ip: ips) {
            tmputxo = new UTXO(ip.prevTxHash, ip.outputIndex);
            if (!pool.contains(tmputxo))
                pool.addUTXO(tmputxo, up.getTxOutput(tmputxo));
            else
                return false;
        }
        // 4,5
        double sumout = 0;
        double sumin = 0;
        for (Transaction.Output tmpop: tx.getOutputs()) {
            if (tmpop.value < 0)
                return false;
            sumout += tmpop.value;
        }
        for (Transaction.Input tmpip: ips) {
            sumin += up.getTxOutput(new UTXO(tmpip.prevTxHash, tmpip.outputIndex)).value;
        }
        if (sumout > sumin) 
            return false;
        return true;
    }

    /**
     * Handles each epoch by receiving an unordered array of proposed transactions, checking each
     * transaction for correctness, returning a mutually valid array of accepted transactions, and
     * updating the current UTXO pool as appropriate.
     */
    public Transaction[] handleTxs(Transaction[] possibleTxs) {
        // IMPLEMENT THIS
        return null;
    }

}
