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
        Transaction.Output top;
        Transaction.Input tip;
        for (int i = 0; i < tx.numInputs(); i++) {
            tip = tx.getInput(i);
            top = up.getTxOutput(new UTXO(tip.prevTxHash, tip.outputIndex));
            if (!Crypto.verifySignature(top.address, tx.getRawDataToSign(i), tip.signature))
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
        TxHandler tmptx = this;
        /*
        for (UTXO ut: tmptx.up.getAllUTXO()) {
            tmptx.up.addUTXO(ut, up.getTxOutput(ut));
        }
        */
        ArrayList<Transaction> trarray = new ArrayList<Transaction>();
        for (Transaction tr: possibleTxs) {
            if (tmptx.isValidTx(tr)) {
                for (Transaction.Input ip: tr.getInputs()) {
                    tmptx.up.removeUTXO(new UTXO(ip.prevTxHash, ip.outputIndex));
                }
                for (int i = 0; i < tr.numOutputs(); i++) {
                    tmptx.up.addUTXO(new UTXO(tr.getHash(), i), tr.getOutput(i));
                }
                trarray.add(tr);
            }
        }
        Transaction[] tran = new Transaction[trarray.size()];
        int i = 0;
        for (Transaction tr: trarray)
            tran[i++] = tr;
        return tran;
    }
}
