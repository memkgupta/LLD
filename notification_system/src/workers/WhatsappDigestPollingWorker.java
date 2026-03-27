package workers;

import dtos.NotificationJob;
import dtos.NotificationSuccess;
import dtos.WhatsappNotificationJob;
import messages.StringMessage;
import messages.WhatsappDigestMessage;
import queues.BiNotificationQueue;
import queues.WhatsappDigestQueue;

/**
 * U → String
 *     Raw content type (individual messages before batching)
 *
 * D → WhatsappDigestMessage
 *     Final DIGESTED message (contains List<String>)
 *
 * T → WhatsappNotificationJob
 *     Final job that wraps the digest message and is executed by worker
 *
 * So pipeline is:
 *
 * String (content)
 *   → StringMessage
 *   → NotificationJob<StringMessage>
 *   → Digest (group multiple messages)
 *   → WhatsappDigestMessage
 *   → WhatsappNotificationJob
 *   → processed by this worker
 */
public class WhatsappDigestPollingWorker extends DigestPollingWorker<
        String,                     // U → raw content type
        WhatsappDigestMessage,     // D → digest message type
        WhatsappNotificationJob    // T → final executable job
        > {

    /**
     * Queue contract:
     *
     * Input side:
     *   ? extends NotificationJob<StringMessage>
     *   → allows flexibility:
     *     - NotificationJob<StringMessage>
     *     - or subclasses of it
     *
     * Output side:
     *   WhatsappNotificationJob
     *   → MUST match worker's expected job type
     *
     * WHY '? extends' here?
     * → Because Java generics are invariant
     * → So this allows passing:
     *      NotificationJob<StringMessage>
     *      OR subclasses of it
     *
     *
     */
    public WhatsappDigestPollingWorker(
           WhatsappDigestQueue queue
    ) {
        super(queue);
    }

    /**
     * Actual processing logic
     *
     * This is called after:
     * - messages are batched
     * - digest job is created
     * - worker picks it from queue
     */
    @Override
    protected NotificationSuccess task(Object job) {
        // TODO: send WhatsApp message, call API, etc.
        return null;
    }
}